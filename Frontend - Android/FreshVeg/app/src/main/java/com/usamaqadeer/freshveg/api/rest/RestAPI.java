package com.usamaqadeer.freshveg.api.rest;

import com.usamaqadeer.freshveg.api.models.CategoriesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {

    @POST("values/AdminLogin")
    Call<String> loginAdmin(
            @Query("email") String email,
            @Query("password") String password
    );

    @GET("values/ViewCategory")
    Call<List<CategoriesModel>> getCategories();


}
