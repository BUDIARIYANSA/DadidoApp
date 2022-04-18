package com.example.dadidoapp;
import com.example.dadidoapp.Model.Creator;
import com.example.dadidoapp.Model.Item;

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
    Call<ArrayList<Item>> newItem(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Item>> mostExpensive(@Body RequestBody body);

    @POST("webservice.php")
    Call<ArrayList<Creator>> cardCreator(@Body RequestBody body);
}
