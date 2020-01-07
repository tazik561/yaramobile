package com.yaramobile.batman.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailBatmanViewModelFactory implements ViewModelProvider.Factory {

    private String imdbID;

    public DetailBatmanViewModelFactory(String imdbID) {
        this.imdbID = imdbID;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailBatmanViewModel.class)) {
            return (T) new DetailBatmanViewModel(imdbID);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
