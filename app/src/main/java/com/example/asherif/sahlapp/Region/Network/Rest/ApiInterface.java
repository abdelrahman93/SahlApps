package com.example.asherif.sahlapp.Region.Network.Rest;

import com.example.asherif.sahlapp.Region.Network.Model.File;
import com.example.asherif.sahlapp.Region.Network.Model.FileContent;
import com.example.asherif.sahlapp.Region.Network.Model.User;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("MCLoginMobiledocs/JAAwAGYAVABAAHgAVgBsAEYAeABfAHMAYwBTACEANQBNADIAeABTAA==/{ServerIP}/{DBName}/{UserName}/{Password}")
    Call<User>getLoginProcess(@Path("ServerIP") String ServerIP, @Path("DBName") String DBName, @Path("UserName") String UserName, @Path("Password") String Password);


    @POST ("AddEditBarCodeFile/TQAwAGIAQABQAGEAJAAkADQAVwBlAGIAJABzAGUAcgB2AGkAYwBlAA==/104.46.55.15/osama_demo")
    Call<File>createFile(@Body File file);

    @POST ("AddEditBarCodeFileContent/TQAwAGIAQABQAGEAJAAkADQAVwBlAGIAJABzAGUAcgB2AGkAYwBlAA==/104.46.55.15/osama_demo")
    Call<FileContent>AddFileContent(@Body FileContent fileContent);
}
