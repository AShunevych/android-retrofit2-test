package com.ashunevich.android_retrofit2_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
 //   private static final String CAT_URL = "https://cat-fact.herokuapp.com/";

    //Use demo address here like "https://my-json-server.typicode.com/<NAME>/<Repo Name>/"
  private static final String DEMO_URL = "https://my-json-server.typicode.com/AShunevych/retrofitJSONTest/";
    private final Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(DEMO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public TestAPI getJSONApi() {
        return mRetrofit.create(TestAPI.class);
    }
}
