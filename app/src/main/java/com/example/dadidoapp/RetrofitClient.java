package com.example.dadidoapp;

import java.net.InetAddress;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    
    private static final String baseURL = "http://192.168.100.189/Webservice-Dadido/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
