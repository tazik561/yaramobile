package com.yaramobile.batman.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.yaramobile.batman.R;
import com.yaramobile.batman.database.AppDatabase;
import com.yaramobile.batman.databinding.FragmentBatmanBinding;

public class BatmanFragment extends Fragment {

    private FragmentBatmanBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_batman, container, false);


        BatmanViewModelFactory factory = new BatmanViewModelFactory(
                AppDatabase.getInstance(getContext()).batmanDao());

        BatmanViewModel model = ViewModelProviders.of(this,
                factory).get(BatmanViewModel.class);

        BatmanRecyclerAdapter adapter = new BatmanRecyclerAdapter(batmanEntity -> {
            Toast.makeText(getContext(), batmanEntity.getImdbID(), Toast.LENGTH_SHORT).show();
            Navigation.findNavController(binding.getRoot())
                    .navigate(BatmanFragmentDirections.actionBatmanFragmentToDetailBatmanFragment(batmanEntity.getImdbID()));
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.setLifecycleOwner(this);
        binding.recyclerView.setAdapter(adapter);

        model.getBatman().observe(this, adapter::submitList);
        model.getBatmanList();
        return binding.getRoot();
    }

}
