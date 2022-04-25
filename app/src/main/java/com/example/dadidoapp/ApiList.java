package com.example.dadidoapp;
import com.example.dadidoapp.Model.Collection;
import com.example.dadidoapp.Model.Creator;
import com.example.dadidoapp.Model.Item;
import com.example.dadidoapp.Model.ItemCollection;
import com.example.dadidoapp.Model.Profile;
import com.example.dadidoapp.Model.User;
import com.example.dadidoapp.Model.Wallet;

import java.util.ArrayList;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiList {

    @POST("webservice.php")
    Call<Object> login(@Body RequestBody body);

    @POST("webservice.php")
    Call<Object> register(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Item>> favorite(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Item>> newItem(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Item>> mostExpensive(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Creator>> cardCreator(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Profile>> profile(@Body RequestBody body);

    @POST("webservice.php")
    Call<Object> updateProfile(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Item>> searchItem(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Wallet>> getWallet(@Body RequestBody body);

    @POST("webservice.php")
    Call<Object> deposit(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Item>> itemById(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<ItemCollection>> itemDetail(@Body RequestBody body);

    @POST("webservice.php")
    Call<Object> createItem(@Body RequestBody body);

    @POST("webservice.php")
    Call<Object> favItem(@Body RequestBody body);

}
