package com.ashunevich.android_retrofit2_test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TestAPI {

    // !========for cats========!
    @GET("facts/random")
    Call<ItemJSON> getFact();

    @GET("facts/random?amount=2")
    Call<List<ItemJSON>> getFacts();
    // !========for cats========!

    // !========for  typicode demo========!
    @GET("posts")
    Call<List<ItemJSON>> getPost();

    @GET("posts/{id}")
    Call<ItemJSON> getPostByID(@Path("id") int id);

    @POST("posts")
    Call<ItemJSON> newPost(@Body ItemJSON itemJSON);

    @DELETE("posts/{id}")
    Call<ItemJSON> deletePost(@Path("id") int id);

    @PUT("posts/{id}")
    Call<ItemJSON> putPost(@Path("id") String id, @Body ItemJSON itemJSON );

    @PATCH("posts/{id}")
    Call<ItemJSON> patchPost(@Path("id") String id,@Body ItemJSON itemJSON );

}
