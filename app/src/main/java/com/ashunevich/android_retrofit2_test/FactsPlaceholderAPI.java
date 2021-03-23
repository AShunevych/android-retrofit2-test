package com.ashunevich.android_retrofit2_test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FactsPlaceholderAPI {
    @GET("facts/random")
    Call<ItemJSON> getFact();

    @GET("facts/random?amount=2")
    Call<List<ItemJSON>> getFacts();

}
