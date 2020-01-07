package com.yaramobile.batman.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yaramobile.batman.Service.BaseServiceHelper;
import com.yaramobile.batman.Service.BatmanServiceHelper;
import com.yaramobile.batman.database.MovieDetailEntity;

public class DetailBatmanViewModel extends ViewModel {

    public static final String TAG = DetailBatmanViewModel.class.getName();

    private MutableLiveData<MovieDetailEntity> _liveData = new MutableLiveData<>();
    public LiveData<MovieDetailEntity>  getMovie() {
        return _liveData;
    }

    private String imdbID = "";

    public DetailBatmanViewModel(String imdbID) {
        this.imdbID = imdbID;
    }

    public void searchMovie() {
        BatmanServiceHelper.searchMovie(imdbID, new BaseServiceHelper.Callback<MovieDetailEntity>() {
            @Override
            public void onResponse(MovieDetailEntity response) {
                _liveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
