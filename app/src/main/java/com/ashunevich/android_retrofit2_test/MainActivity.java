package com.ashunevich.android_retrofit2_test;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


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
    private List<ItemJSON> ItemJSONList = new ArrayList<>();
    private List<ItemJSON> lists =  new ArrayList<>();
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRecyclerView();

        binding.button.setOnClickListener(view ->getItem() );
        binding.button2.setOnClickListener(view -> postItem());

    }

    private void setRecyclerView() {
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(ItemJSONList);
        adapter.setListContent(ItemJSONList);
        binding.recView.setAdapter(adapter);
    }
    // !========for demo========!
     void getItem(){
         NetworkService.getInstance().getJSONApi().getPost().enqueue(new Callback<List<ItemJSON>>() {
             @Override
             public void onResponse(@NonNull Call<List<ItemJSON>> call, @NonNull Response<List<ItemJSON>> response) {
                 adapter.updateList(response.body());
                     Log.d("OPERATION @GET", "CALLBACK SUCCESSFUL");

             }

             @Override
             public void onFailure(@NonNull Call<List<ItemJSON>>call, @NonNull Throwable t) {
                 Log.d("OPERATION @GET", "CALLBACK FAILURE");
                 t.printStackTrace();
             }
         });
     }

     void postItem(){
        NetworkService.getInstance().getJSONApi().newPost(itemJSON(returnStringFroMTextEdit(binding.id),returnStringFroMTextEdit(binding.textPost)))
                .enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call, @NonNull Response<ItemJSON> response) {
                //since it's fake REST API it would only return callback status
                ItemJSON itemJSON = response.body();
                ItemJSONList.add(itemJSON);
                adapter.notifyItemInserted(adapter.getItemCount());
               Log.d("OPERATION @POST","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call, @NonNull Throwable t) {
                Log.d("OPERATION @POST","CALLBACK FAILURE");
            }
        });
    }

     ItemJSON itemJSON(String id,String post){
        return new ItemJSON(id,post);
    }

     String returnStringFroMTextEdit(EditText text){
        if(text.getText().toString().isEmpty()){
            return "";
        }
        else
        {
            return text.getText().toString();
        }
    }

    // !========for cats========!
    //  use this methods  to get some real data for test
     void getSingleFactResponse() {
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

     void getTwoFactsResponse() {
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

