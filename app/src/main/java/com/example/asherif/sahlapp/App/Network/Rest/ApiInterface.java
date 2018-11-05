package com.example.asherif.sahlapp.App.Network.Rest;

import com.example.asherif.sahlapp.App.Network.Model.File;
import com.example.asherif.sahlapp.App.Network.Model.FileContent;
import com.example.asherif.sahlapp.App.Network.Model.User;
import com.example.asherif.sahlapp.App.Region.City;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("MCLoginMobiledocs/JAAwAGYAVABAAHgAVgBsAEYAeABfAHMAYwBTACEANQBNADIAeABTAA==/{ServerIP}/{DBName}/{UserName}/{Password}")
    Call<User>getLoginProcess(@Path("ServerIP") String ServerIP, @Path("DBName") String DBName, @Path("UserName") String UserName, @Path("Password") String Password);


    @POST ("AddEditBarCodeFile/TQAwAGIAQABQAGEAJAAkADQAVwBlAGIAJABzAGUAcgB2AGkAYwBlAA==/104.46.55.15/osama_demo")
    Call<File>createFile(@Body File file);

    @POST ("AddEditBarCodeFileContent/TQAwAGIAQABQAGEAJAAkADQAVwBlAGIAJABzAGUAcgB2AGkAYwBlAA==/104.46.55.15/osama_demo")
    Call<FileContent>AddFileContent(@Body FileContent fileContent);

    @Headers({
            "x-api-key: k4o8ocs8skg8os88o8k4kc0kcgosc8cwkkcc4gsc",
            "Content-Type: application/x-www-form-urlencoded"})
    @POST ("login/")
    Call<User>Login(@Body User User);
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
