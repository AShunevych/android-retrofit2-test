package com.ashunevich.android_retrofit2_test;

import android.os.Bundle;
import android.util.Log;


import com.ashunevich.android_retrofit2_test.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<ItemJSON> ItemJSONList = new ArrayList<>();
    private List<ItemJSON> lists =  new ArrayList<>();
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRecyclerView();
        binding.button.setOnClickListener(view -> getSingleFactResponse());
        binding.button2.setOnClickListener(view -> getTwoFactsResponse());

    }

    private void setRecyclerView() {
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(ItemJSONList);
        adapter.setListContent(ItemJSONList);
        binding.recView.setAdapter(adapter);
    }

    private void getSingleFactResponse() {
        NetworkService.getInstance().getJSONApi().getFact().enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call, @NonNull Response<ItemJSON> response) {
                if (response.body() != null) {
                    ItemJSON itemJSON = response.body();
                    ItemJSONList.add(itemJSON);
                    adapter.notifyItemInserted(adapter.getItemCount());
                    Log.d("SUCCESS", "CALLBACK SUCCESSFUL");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call, @NonNull Throwable t) {
                Log.d("ERROR", "CALLBACK FAILURE");
            }
        });
    }

    private void getTwoFactsResponse() {
        NetworkService.getInstance().getJSONApi().getFacts().enqueue(new Callback<List<ItemJSON>>() {
            @Override
            public void onResponse(@NonNull Call<List<ItemJSON>> call, @NonNull Response<List<ItemJSON>> response) {
                if (response.body() != null) {
                    lists = response.body();;
                    adapter.updateList(lists);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ItemJSON>> call, @NonNull Throwable t) {

            }
        });
    }
}

