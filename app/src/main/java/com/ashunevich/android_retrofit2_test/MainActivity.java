package com.ashunevich.android_retrofit2_test;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;


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

        binding.getButton.setOnClickListener(view ->getItem() );
        binding.postButton.setOnClickListener(view -> postItem());
        binding.patchButton.setOnClickListener(view -> patchItem());
        binding.deleteButton.setOnClickListener(view -> deleteItem());
        binding.putButton.setOnClickListener(view -> replacePost());
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
                 setStatus("OPERATION @GET " + "CALLBACK SUCCESSFUL");
                     Log.d("OPERATION @GET", "CALLBACK SUCCESSFUL");

             }

             @Override
             public void onFailure(@NonNull Call<List<ItemJSON>>call, @NonNull Throwable t) {
                 setStatus("OPERATION @GET " + "CALLBACK FAILURE");
                 Log.d("OPERATION @GET", "CALLBACK FAILURE");
                 t.printStackTrace();
             }
         });
     }

     void postItem(){
        NetworkService.getInstance().getJSONApi().newPost(itemJSON())
                .enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call, @NonNull Response<ItemJSON> response) {
                //since it's fake REST API it would return callback
                ItemJSON itemJSON = response.body();
                ItemJSONList.add(itemJSON);
                adapter.notifyItemInserted(adapter.getItemCount());
                setStatus("OPERATION @POST " + "CALLBACK SUCCESSFUL");
               Log.d("OPERATION @POST","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call, @NonNull Throwable t) {
                setStatus("OPERATION @POST " + "CALLBACK FAILURE");
                Log.d("OPERATION @POST","CALLBACK FAILURE");
            }
        });
    }
    //PUT AND PATCH DOESN'T WORK PROPERLY SINCE I DON"T USE REAL API
    void replacePost(){
        NetworkService.getInstance().getJSONApi().putPost(returnStringFroMTextEdit(binding.id),itemJSON()).enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call,@NonNull Response<ItemJSON> response) {
                //if it used as replace method than there should be method to delete old value and put new on the same place
                //but here it used as alternative @POST method
                //in case of use of Room / SQL  @Update logic can be used
                setStatus("OPERATION  @PUT " + "CALLBACK SUCCESSFUL");
                Log.d("OPERATION @PUT","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call,@NonNull Throwable t) {
                setStatus("OPERATION  @PUT " + "CALLBACK FAILURE");
                Log.d("OPERATION  @PUT","CALLBACK FAILURE");
            }
        });
    }


    void deleteItem(){
        NetworkService.getInstance().getJSONApi().deletePost(returnStringFroMTextEdit(binding.id)).enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call,@NonNull Response<ItemJSON> response) {
                setStatus("OPERATION @DELETE " + "CALLBACK SUCCESSFUL");
                Log.d("OPERATION @DELETE","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call,@NonNull Throwable t) {
                setStatus("OPERATION @DELETE " + "CALLBACK FAILURE");
                Log.d("OPERATION @DELETE","CALLBACK FAILURE");
            }
        });
    }

    //PUT AND PATCH DOESN'T WORK PROPERLY SINCE I DON"T USE REAL API
    void patchItem(){
        NetworkService.getInstance().getJSONApi().patchPost(returnStringFroMTextEdit(binding.id),itemJSON()).enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call,@NonNull Response<ItemJSON> response) {
                setStatus("OPERATION @PATCH " + "CALLBACK SUCCESSFUL");
                Log.d("OPERATION @PATCH","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call,@NonNull Throwable t) {
                setStatus("OPERATION @PATCH " + "CALLBACK FAILURE");
                Log.d("OPERATION @PATCH","CALLBACK FAILURE");
            }
        });
    }


     ItemJSON itemJSON(){
        return new ItemJSON(returnStringFroMTextEdit(binding.textPost),returnStringFroMTextEdit(binding.id));
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

    void setStatus(String string){
        binding.operationStatus.setText(string);
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

