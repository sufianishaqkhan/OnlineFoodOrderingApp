package com.usamaqadeer.freshveg.api.rest;

import com.usamaqadeer.freshveg.api.models.CategoriesModel;
import com.usamaqadeer.freshveg.api.models.DeliveryBoysModel;
import com.usamaqadeer.freshveg.api.models.OrderAssignsModel;
import com.usamaqadeer.freshveg.api.models.OrderDetailsModel;
import com.usamaqadeer.freshveg.api.models.ProductsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {

    /*LOGIN APIS*/
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

    /*PRODUCTS AND CATEGORIES APIS*/
    @GET("Product/GetProducts")
    Call<List<ProductsModel>> getProducts();

    @GET("Product/GetCategories")
    Call<List<CategoriesModel>> getCategories();

    @POST("Admin/InsertCategory")
    Call<String> postCategory(
            @Query("category") String category
    );

    @POST("Admin/InsertProduct")
    Call<String> postProduct(
            @Query("p_name") String p_name,
            @Query("p_category") int p_category,
            @Query("p_unitprice") String p_unitprice,
            @Query("p_qty") String p_qty,
            @Query("p_weight") String p_weight
    );

    /*DELIVERY BOYS APIS*/
    @GET("DeliveryBoy/GetDeliveryBoys")
    Call<List<DeliveryBoysModel>> getDeliveryBoys();

    @POST("Admin/InsertDeliveryBoy")
    Call<String> postDeliveryBoy(
            @Query("db_name") String name,
            @Query("db_email") String email,
            @Query("db_password") String password,
            @Query("db_shiftstart") String shiftStart,
            @Query("db_shiftend") String shiftEnd
    );

    /*ORDERS APIS*/
    @GET("Order/GetOrderDetails")
    Call<List<OrderDetailsModel>> getOrderDetails();

    @GET("Order/GetOrderAssignDetails")
    Call<List<OrderAssignsModel>> getOrderAssignDetails();

    @POST("User/PostOrderDetails")
    Call<String> postOrder(
            @Query("od_user_id") String od_user_id,
            @Query("od_pid") String od_pid,
            @Query("od_qty") String od_qty,
            @Query("od_delivered_loc") String od_delivered_loc,
            @Query("od_price") String od_price
    );
}
