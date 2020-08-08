package com.usamaqadeer.freshveg.api.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static Retrofit retrofit;
    public static final String BASE_URL = "http://10.0.2.2/OnlineFoodOrderingSystem/";
    public static final String API_URL = "http://10.0.2.2/OnlineFoodOrderingSystem/api/";
    public static final String IMAGE_URL = "http://10.0.2.2/OnlineFoodOrderingSystem/Images/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
