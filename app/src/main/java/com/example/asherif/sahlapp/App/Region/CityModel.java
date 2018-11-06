package com.example.asherif.sahlapp.App.Region;

import android.content.SharedPreferences;

import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class CityModel {
    ApiInterface apiInterface;
    private ArrayList<String> EGYPT_CITYLIST ;
    String deviceid;
    SharedPreferences pref;

    public CityModel(String deviceid) {
        this.deviceid = deviceid;
    }


}
