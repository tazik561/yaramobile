package com.yaramobile.batman.Service;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.Okio;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseServiceHelper<S> {

    protected static final int TIMEOUT = 60;

    protected Retrofit retrofit;

    protected S svc;

    private HttpLoggingInterceptor makeInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }




    protected BaseServiceHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .client(new OkHttpClient.Builder()
                        .cache(null)
                        .connectTimeout(TIMEOUT / 2, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(chain -> {
                            Request request = chain.request();
                            if (request.body() != null) {
                                Buffer buffer = new Buffer();
                                request.body().writeTo(buffer);
                                Log.i("=====", buffer.readUtf8());
                            }
                            okhttp3.Response response = chain.proceed(request);
                            ResponseBody body = response.body();
                            try {
                                String content = manipulateResponse(body.string());
                                if (TextUtils.isEmpty(content)) return response;
                                InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
                                ResponseBody responseBody = ResponseBody.create(
                                        body.contentType(),
                                        //body.contentLength(),
                                        content.length(),
                                        Okio.buffer(Okio.source(stream)));
                                return response.newBuilder().body(responseBody).build();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.i("=====", "BaseServiceHelper: " + e.getMessage());
                            }
                            return response;
                        })
                        .addInterceptor(makeInterceptor())
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    protected String manipulateResponse(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            if (obj.get(obj.keys().next()) instanceof JSONArray) {
                return obj.getJSONArray(obj.keys().next()).toString();
            } else {
                return obj.toString();
            }

        } catch (Exception e) {
            Log.e("====", "manipulateResponse: ", e);
        }
        return null;
    }

    protected static <T> retrofit2.Callback<T> createCallback(final Callback<T> callback) {
        return new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(response.body());
                    }
                } else {
                    onFailure(call,
                            new Exception(response.code() + " " + call.request().url().toString()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(t);
                }
            }
        };
    }


    public interface Callback<T> {
        void onResponse(T response);

        void onFailure(Throwable t);
    }
}
