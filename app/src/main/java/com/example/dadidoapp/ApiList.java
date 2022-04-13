package com.example.dadidoapp;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiList {

    @POST("webservice.php")
    Call<Object> login(@Body RequestBody body);

    @POST("webservice.php")
    Call<Object> register(@Body RequestBody body);

}
