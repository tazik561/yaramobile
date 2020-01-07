package com.yaramobile.batman.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.yaramobile.batman.database.BatmanEntity;
import com.yaramobile.batman.databinding.BatmansItemBinding;


public class BatmanRecyclerAdapter extends ListAdapter<BatmanEntity, BatmanRecyclerAdapter.ViewHolder> {

    public BatmanClickListener clickListener;

    public BatmanRecyclerAdapter(BatmanClickListener clickListener) {
        super(BatmanEntity.DIFF_CALLBACK);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BatmanEntity item = getItem(position);
        holder.bind(item, clickListener);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private BatmansItemBinding binding;

        private ViewHolder(@NonNull BatmansItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private static ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            BatmansItemBinding binding = BatmansItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }


        private void bind(BatmanEntity item, BatmanClickListener clickListener) {
            binding.setBatman(item);
            binding.setClickListener(clickListener);
            binding.executePendingBindings();
        }
    }

    public interface BatmanClickListener {
        void onItemClick(BatmanEntity batmanEntity);
    }
}


