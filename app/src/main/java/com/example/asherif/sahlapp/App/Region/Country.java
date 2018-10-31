package com.example.asherif.sahlapp.App.Region;

import android.util.Log;

import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Country {
    private static ArrayList<String> COUNTRYLIST;
//    private String mCountryName;
//    private int mFlagImage;


    public static void setCOUNTRYLIST(ArrayList<String> COUNTRYLIST) {
        Country.COUNTRYLIST = COUNTRYLIST;
    }

    public ArrayList<String> getCOUNTRYLIST() {
        COUNTRYLIST = new ArrayList<String>();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        com.example.asherif.sahlapp.App.Network.Model.Country user = new com.example.asherif.sahlapp.App.Network.Model.Country();
        Call<com.example.asherif.sahlapp.App.Network.Model.Country> callFile = apiInterface.CountryRegion(user);
        callFile.enqueue(new Callback<com.example.asherif.sahlapp.App.Network.Model.Country>() {
            @Override
            public void onResponse(Call<com.example.asherif.sahlapp.App.Network.Model.Country> call, Response<com.example.asherif.sahlapp.App.Network.Model.Country> response) {
                Log.i("Resopns_notNill", "statusresponse" + String.valueOf(response.body().getStatus()));
                Log.i("Resopns_notNill", "statusresponse" + String.valueOf(response.body().getCountries().get(0).getId()));

                for (int i = 0; i < response.body().getCountries().size(); i++) {
                    COUNTRYLIST.add(response.body().getCountries().get(i).getId());
                }
                Log.i("TAG", "COUNTRYLISTCOUNTRYLISTCOUNTRYLISTCOUNTRYLIST: " + COUNTRYLIST);
                COUNTRYLIST.add(response.body().getCountries().get(1).getName());
                //  COUNTRYLIST.add(response.body().getCountries().get(i).getId());


            }

            @Override
            public void onFailure(Call<com.example.asherif.sahlapp.App.Network.Model.Country> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
            }
        });
        for (int i = 0; i < COUNTRYLIST.size(); i++) {
            Log.i("TAG", "COUNTRYLIST: " + COUNTRYLIST.get(i));
        }

        return COUNTRYLIST;
    }


}
