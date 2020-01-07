package com.yaramobile.batman.detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.yaramobile.batman.R;
import com.yaramobile.batman.database.BatmanEntity;
import com.yaramobile.batman.databinding.FragmentDetailBatmanBinding;

import java.util.Objects;

public class DetailBatmanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentDetailBatmanBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_detail_batman,
                        container, false);


        String imdbID = DetailBatmanFragmentArgs.fromBundle(
                Objects.requireNonNull(getArguments())).getImdbID();

        DetailBatmanViewModelFactory factory =
                new DetailBatmanViewModelFactory(imdbID);

        DetailBatmanViewModel model = ViewModelProviders
                .of(this, factory)
                .get(DetailBatmanViewModel.class);

        model.searchMovie();

        model.getMovie().observe(this, binding::setMovie);
        return binding.getRoot();
    }

}
