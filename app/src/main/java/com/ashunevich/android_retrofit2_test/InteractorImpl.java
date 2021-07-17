package com.ashunevich.android_retrofit2_test;

import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InteractorImpl implements Contractor.Interactor{

    @Override
    public void getPosts(onGetPostsListener onGetPostsListener) {
        NetworkService.getInstance().getJSONApi().getPost().enqueue(new Callback<List<ItemJSON>> () {
            @Override
            public void onResponse(@NonNull Call<List<ItemJSON>> call, @NonNull Response<List<ItemJSON>> response) {
                Log.d("OPERATION @GET","CALLBACK SUCCESSFUL");
                onGetPostsListener.onFinished (response.body ());
            }

            @Override
            public void onFailure(@NonNull Call<List<ItemJSON>>call, @NonNull Throwable t) {
                Log.d("OPERATION @GET","CALLBACK FAILURE");
                onGetPostsListener.onFailure (t);
            }
        });
    }

    @Override
    public void newPosts(onNewPostListener onNewPostListener, ItemJSON item) {
        NetworkService.getInstance().getJSONApi().newPost(item)
                .enqueue(new Callback<ItemJSON>() {
                    @Override
                    public void onResponse(@NonNull Call<ItemJSON> call, @NonNull Response<ItemJSON> response) {
                        //since it's fake REST API it would return callback
                        Log.d("OPERATION @POST","CALLBACK SUCCESSFUL");
                        onNewPostListener.onFinished (response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<ItemJSON> call, @NonNull Throwable t) {
                        Log.d("OPERATION @POST","CALLBACK FAILURE");
                        onNewPostListener.onFailure (t);
                    }
                });
    }

    @Override
    public void deletePost(onDeletePostListener onDeletePostListener, String id) {
        NetworkService.getInstance().getJSONApi().deletePost(id).enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call,@NonNull Response<ItemJSON> response) {
                assert response.body () != null;
                onDeletePostListener.onFinished (response.body ().getFactText ());
                Log.d("OPERATION @DELETE","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call,@NonNull Throwable t) {
                onDeletePostListener.onFailure (t);
                Log.d("OPERATION @DELETE","CALLBACK FAILURE");
            }
        });
    }

    @Override
    public void patchPost(onPatchPostListener onPatchPostListener, String id, ItemJSON itemJSON) {
        NetworkService.getInstance().getJSONApi().patchPost(id,itemJSON).enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call,@NonNull Response<ItemJSON> response) {
                onPatchPostListener.onFinished (id,itemJSON);
                Log.d("OPERATION @PATCH","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call,@NonNull Throwable t) {
                onPatchPostListener.onFailure (t);
                Log.d("OPERATION @PATCH","CALLBACK FAILURE");
            }
        });
    }

    @Override
    public void putPost(Contractor.Interactor.onPutPostListener onPutPostListener, String id, ItemJSON itemJSON) {
        NetworkService.getInstance().getJSONApi().putPost (id,itemJSON).enqueue(new Callback<ItemJSON>() {
            @Override
            public void onResponse(@NonNull Call<ItemJSON> call,@NonNull Response<ItemJSON> response) {
                onPutPostListener.onFinished (id,itemJSON);
                Log.d("OPERATION @PUT","CALLBACK SUCCESSFUL");
            }

            @Override
            public void onFailure(@NonNull Call<ItemJSON> call,@NonNull Throwable t) {
                onPutPostListener.onFailure (t);
                Log.d("OPERATION @PUT","CALLBACK FAILURE");
            }
        });
    }


}
