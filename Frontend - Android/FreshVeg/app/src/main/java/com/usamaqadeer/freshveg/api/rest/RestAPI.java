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

    /* LOGIN APIS */
    @POST("Admin/LoginAdmin")
    Call<String> loginAdmin(
            @Query("a_email") String email,
            @Query("a_password") String password
    );

    @POST("DeliveryBoy/LoginDeliveryBoy")
    Call<String> loginDeliveryBoy(
            @Query("db_email") String email,
            @Query("db_password") String password
    );

    @POST("User/LoginUser")
    Call<String> loginUser(
            @Query("u_email") String email,
            @Query("u_password") String password
    );

    /* FORGOT PASSWORD APIS */
    @POST("Admin/ForgotPassword")
    Call<String> forgotPasswordAdmin(
            @Query("a_email") String email,
            @Query("a_password") String password
    );

    @POST("DeliveryBoy/ForgotPassword")
    Call<String> forgotPasswordDeliveryBoy(
            @Query("db_email") String email,
            @Query("db_password") String password
    );

    @POST("User/ForgotPassword")
    Call<String> forgotPasswordUser(
            @Query("u_email") String email,
            @Query("u_password") String password
    );

    @POST("User/PostUser")
    Call<String> postUser(
            @Query("u_name") String name,
            @Query("u_email") String email,
            @Query("u_password") String password
    );

    @GET("Product/GetCategories")
    Call<List<CategoriesModel>> getCategories();

}
