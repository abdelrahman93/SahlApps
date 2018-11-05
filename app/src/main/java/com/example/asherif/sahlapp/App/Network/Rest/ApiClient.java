package com.example.asherif.sahlapp.App.Network.Rest;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.asherif.sahlapp.App.Login.VerificationActivity.TAG;

public class ApiClient {
    public static String BASE_URL;

    public static String getBASE_URL() {
        return BASE_URL;
    }

    public static void setBASE_URL(String BASE_URL) {
        ApiClient.BASE_URL = BASE_URL;
    }

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){

        Log.i(TAG, "getClient: "+getBASE_URL());

        retrofit = new Retrofit.Builder()
                .baseUrl(getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
        }
    }


