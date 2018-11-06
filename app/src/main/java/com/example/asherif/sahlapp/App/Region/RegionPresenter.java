package com.example.asherif.sahlapp.App.Region;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.App.base.BasePresenter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionPresenter extends BasePresenter {
    RegionActivity context;
    private RegionView view;
    public Country country;
    private City city;
    private District district;
    public static ArrayList<String> arrayCountry;
    public static ArrayList<String> arrayCity;


    public RegionPresenter() {
    }

    public RegionPresenter(RegionActivity context, RegionView view, Country country, City city, District district) {
        this.context = context;
        this.view = view;
        this.country = country;
        this.city = city;
        this.district = district;
    }


    //Set default adapter to all spinner before get from API
    public void AddAdapterPresenterDefault(MaterialBetterSpinner countrySpinner, MaterialBetterSpinner citySpinner, MaterialBetterSpinner districtSpinner) {
        ArrayList<String> arr = new ArrayList<String>();
        view.setAdapter(arr, countrySpinner);
        view.setAdapter(arr, citySpinner);
        view.setAdapter(arr, districtSpinner);


    }

    public void AddAdapterPresenterDistrict(String city, MaterialBetterSpinner spinner) {
        view.setAdapter(addDistrictData(city), spinner);

    }

    public void countryAPI(final MaterialBetterSpinner countrySpinner) {
        //Log.i("Resopns_notNill", "testt" +country.getCOUNTRYLIST().get(1));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Country user = new Country();
        Call<com.example.asherif.sahlapp.App.Region.Country> callFile = apiInterface.CountryRegion(user);

        callFile.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                if (response.body() != null && String.valueOf(response.body().getStatus()) == "true") {
                    Log.i("Resopns_notNill", "statusresponse" + String.valueOf(response.body().getStatus()));
                    Log.i("Resopns_notNill", "" + String.valueOf(response.body().getCountries().get(0).getId()));
                    arrayCountry = new ArrayList<String>();
                    for (int i = 0; i < response.body().getCountries().size(); i++) {
                        arrayCountry.add(response.body().getCountries().get(i).getName());
                    }
                    if (arrayCountry != null) {
                        view.setAdapter(arrayCountry, countrySpinner);
                        view.hideProgressBar();
                    }
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Check connection", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void cityAPI(final MaterialBetterSpinner citySpinner, String country) {
        String country_id = null;
        switch (country) {
            case "Egypt":
            case "مصر":
                country_id = "1";
                view.showFlag(R.drawable.egypt_flag);
                break;
            case "Saudi Arabia":
            case "السعودية":

                country_id = "2";
                view.showFlag(R.drawable.ksa_flag);
                break;

            default:
                country_id = "0";
                view.showFlag(0);
        }
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<City> callFile = apiInterface.CityRegion(country_id);

        callFile.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                if (response.body() != null && String.valueOf(response.body().getStatus()) == "true") {
                    arrayCity = new ArrayList<String>();
                    for (int i = 0; i < response.body().getCities().size(); i++) {
                        arrayCity.add(response.body().getCities().get(i).getName());
                    }
                    //   Log.i("Resopns_notNill", "statussecity:  " + arrayCity.get(0));

                    if (arrayCity != null) {
                        view.setAdapter(arrayCity, citySpinner);
                        view.hideProgressBar();
                    }

                } else {
                    arrayCity = new ArrayList<String>();
                    view.setAdapter(arrayCity, citySpinner);
                    Toast.makeText(context, "No cites found", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Check connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public ArrayList<String> addDistrictData(String city) {
        ArrayList<String> arr = new ArrayList<String>();
        switch (city) {
            case "Cairo":
                arr = district.getDISTRICTLIST_Cairo();
                break;
            case "Alex":
                arr = district.getDISTRICTLIST_Alex();
                break;
            default:


        }
        return arr;
    }

    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void RegionData() {

        district.setDISTRICTLIST_Alex();
        district.setDISTRICTLIST_Cairo();
    }

}
