package com.ashunevich.android_retrofit2_test;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import com.ashunevich.android_retrofit2_test.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;



public class MainActivity extends AppCompatActivity implements Contractor.View {

    ActivityMainBinding binding;
    private final List<ItemJSON> ItemJSONList = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Contractor.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRecyclerView();

        presenter = new PresenterImpl (this,new InteractorImpl ());

        //PUT AND PATCH DOESN'T WORK PROPERLY SINCE I DON"T USE REAL API
        binding.getButton.setOnClickListener(view ->presenter.getPosts () );
        binding.postButton.setOnClickListener(view -> presenter.newPost (itemJSON ()));
        binding.patchButton.setOnClickListener(view -> showToast("NOT WORKING"));
        binding.deleteButton.setOnClickListener(view -> presenter.deletePost (returnId(binding.id)));
        binding.putButton.setOnClickListener(view -> presenter.getPostById (returnId(binding.id)));
    }

    private void setRecyclerView() {
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(ItemJSONList);
        adapter.setListContent(ItemJSONList);
        binding.recView.setAdapter(adapter);
    }

    private void showToast(String text){
        Toast.makeText (this,text,Toast.LENGTH_SHORT).show ();
    }

     ItemJSON itemJSON(){
        return new ItemJSON(returnStringFroMTextEdit(binding.textPost),returnId(binding.id));
    }

    int returnId(EditText text){
        if(text.getText().toString().isEmpty()){
            return 0;
        }
        else
        {
            return Integer.parseInt (text.getText ().toString ());
        }
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
    /*
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

     */

    @Override
    public void parseDataToRecyclerView(List<ItemJSON> listCall) {
        adapter.updateList(listCall);
    }

    @Override
    public void createJsonObject(ItemJSON itemJSON) {
        ItemJSONList.add(itemJSON);
        adapter.notifyItemInserted(adapter.getItemCount());
    }

    @Override
    public void setResponseString(int s) {
        showToast("Deleted item id " + s);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText (this,"SOMETHING WENT WRONG",Toast.LENGTH_SHORT).show ();
        Log.d ("THROWABLE",throwable.toString ());
    }
}

