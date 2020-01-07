package com.yaramobile.batman.Service;

import com.yaramobile.batman.database.BatmanEntity;
import com.yaramobile.batman.database.MovieDetailEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class BatmanServiceHelper extends BaseServiceHelper<BatmanServiceHelper.BatmanService> {

    private static BatmanServiceHelper helper = null;

    private BatmanServiceHelper() {
        svc = retrofit.create(BatmanService.class);
    }

    public static BatmanServiceHelper getInstance() {
        if (helper == null) {
            helper = new BatmanServiceHelper();
        }
        return helper;
    }

    public static void getBatmanList(Callback<List<BatmanEntity>> callback) {

        getInstance().
                svc
                .getBatmanList()
                .enqueue(createCallback(callback));
    }

    public static void searchMovie(String imdbId, Callback<MovieDetailEntity> callback) {
        getInstance()
                .svc
                .getMovie(imdbId)
                .enqueue(createCallback(callback));
    }


    public interface BatmanService {

        @GET("/?apikey=3e974fca&s=batman")
        Call<List<BatmanEntity>> getBatmanList();


        @GET("/?apikey=3e974fca")
        Call<MovieDetailEntity> getMovie(@Query("i") String id);

    }
}
