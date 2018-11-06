package com.example.asherif.sahlapp.App.Network.Rest;

import com.example.asherif.sahlapp.App.Login.LoginModel;
import com.example.asherif.sahlapp.App.Network.Model.File;
import com.example.asherif.sahlapp.App.Network.Model.FileContent;
import com.example.asherif.sahlapp.App.Network.Model.User;
import com.example.asherif.sahlapp.App.Region.City;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


    //Function to send sms code to the mobile number
    @Headers({
            "x-api-key: k4o8ocs8skg8os88o8k4kc0kcgosc8cwkkcc4gsc",
            "Content-Type: application/x-www-form-urlencoded"})
    @POST ("login/")
    @FormUrlEncoded
    Call<LoginModel>Login(@Field("phone") String phone,@Field("device_id") String device_id);

    //Function to resend
    //Dynamic header
    @POST ("resend_code/")
    @FormUrlEncoded
    Call<LoginModel>Resend(@Field("phone") String phone,@HeaderMap Map<String, String> apiKey);

    //to Get list of countries
    @Headers({
            "x-api-key: k4o8ocs8skg8os88o8k4kc0kcgosc8cwkkcc4gsc",
            "Content-Type: application/x-www-form-urlencoded"})
    @POST ("countries/")
    Call<com.example.asherif.sahlapp.App.Region.Country>CountryRegion(@Body com.example.asherif.sahlapp.App.Region.Country country);

    //to Get list of cities
    @Headers({
            "x-api-key: k4o8ocs8skg8os88o8k4kc0kcgosc8cwkkcc4gsc",
            "Content-Type: application/x-www-form-urlencoded"})

    @POST ("cities/")
    @FormUrlEncoded
    Call<City>CityRegion(@Field("country_id") String country_id);

}
