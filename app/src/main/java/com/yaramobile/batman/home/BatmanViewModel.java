package com.yaramobile.batman.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yaramobile.batman.Service.BaseServiceHelper;
import com.yaramobile.batman.Service.BatmanServiceHelper;
import com.yaramobile.batman.database.BatmanDao;
import com.yaramobile.batman.database.BatmanEntity;

import java.util.List;

public class BatmanViewModel extends ViewModel {
    private BatmanDao batmanDao;
    private MutableLiveData<List<BatmanEntity>> _batmanVideos = new MutableLiveData<>();

    public BatmanViewModel(BatmanDao batmanDao) {
        this.batmanDao = batmanDao;
    }

    public LiveData<List<BatmanEntity>> getBatman() {
        return _batmanVideos;
    }

    public void getBatmanList() {
        BatmanServiceHelper.getBatmanList(
                new BaseServiceHelper.Callback<List<BatmanEntity>>() {
                    @Override
                    public void onResponse(List<BatmanEntity> response) {
                        _batmanVideos.setValue(response);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }
}
