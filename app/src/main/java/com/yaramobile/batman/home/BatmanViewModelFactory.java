package com.yaramobile.batman.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yaramobile.batman.database.BatmanDao;


public class BatmanViewModelFactory implements ViewModelProvider.Factory {
    private BatmanDao batmanDao;

    BatmanViewModelFactory(BatmanDao batmanDao) {
        this.batmanDao = batmanDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BatmanViewModel.class)) {
            return (T) new BatmanViewModel(batmanDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
