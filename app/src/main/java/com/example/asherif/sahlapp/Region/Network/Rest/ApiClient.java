package com.example.asherif.sahlapp.Region.Network.Rest;


import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://sahl-app.com/api/user/";


    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

    //Create a new Interceptor.
    Interceptor headerAuthorizationInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();
            Headers headers = request.headers().newBuilder().add("x-api-key", "k4o8ocs8skg8os88o8k4kc0kcgosc8cwkkcc4gsc").build();
            request = request.newBuilder().headers(headers).build();
            return chain.proceed(request);
        }
    };

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
        }
    }


