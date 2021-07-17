package com.ashunevich.android_retrofit2_test;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ashunevich.android_retrofit2_test.databinding.JsonItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>  {
    private List<ItemJSON> pad_list;

    public RecyclerViewAdapter(List<ItemJSON> data){
        this.pad_list = data;
    }

    //This method inflates view present in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(JsonItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    //Binding the data using get() method of POJO object
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        ItemJSON item = pad_list.get(position);
        holder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        return pad_list.size();
    }

    public void setListContent(List<ItemJSON> pad_list) {
        this.pad_list = pad_list;
    }

    protected void updateList(List<ItemJSON> facts){
        final DiffUtiiCallbackAdapter adapter = new DiffUtiiCallbackAdapter(this.pad_list, facts);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(adapter);

        this.pad_list.addAll(facts);
        diffResult.dispatchUpdatesTo(this);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        final JsonItemBinding binding;

        public MyViewHolder(JsonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindItem(ItemJSON item){
            binding.setFactText(item.getFactText());
            binding.setNumber(item.getFactNumber());
            binding.executePendingBindings();
        }
    }
}
