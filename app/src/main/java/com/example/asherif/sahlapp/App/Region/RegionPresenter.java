package com.example.asherif.sahlapp.App.Region;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.App.base.BasePresenter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionPresenter extends BasePresenter {
    RegionActivity context;
    private RegionView view;
    private  Country country;
    private City city;
    private District district;

    public RegionPresenter() {
    }

    public RegionPresenter( RegionActivity context,RegionView view, Country country, City city, District district) {
        this.context=context;
        this.view = view;
        this.country = country;
        this.city = city;
        this.district = district;
    }

    public ArrayList<String> addDistrictData(String city){
        ArrayList<String> arr=new ArrayList <String>();
        switch (city){
            case "Cairo":        arr= district.getDISTRICTLIST_Cairo();
                break;
            case "Alex": arr= district.getDISTRICTLIST_Alex();
                break;
                default:


        }
        return arr;
    }
    //Set default adapter to all spinner before get from API
    public void AddAdapterPresenterCity(MaterialBetterSpinner countrySpinner,MaterialBetterSpinner citySpinner,MaterialBetterSpinner districtSpinner){
        ArrayList<String> arr=new ArrayList <String>();
        view.setAdapter(arr,countrySpinner);
        view.setAdapter(arr,citySpinner);
        view.setAdapter(arr,districtSpinner);


    }
    public void AddAdapterPresenterDistrict(String city,MaterialBetterSpinner spinner){
        view.setAdapter(addDistrictData(city),spinner);

    }
    public  void countryAPI(final MaterialBetterSpinner countrySpiner) {

    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    Country user = new Country();
    Call<com.example.asherif.sahlapp.App.Region.Country> callFile = apiInterface.CountryRegion(user);

        callFile.enqueue(new Callback<Country>() {
        @Override
        public void onResponse(Call<Country> call, Response<Country> response) {
            Log.i("Resopns_notNill", "statusresponse" + String.valueOf(response.body().getStatus()));
            Log.i("Resopns_notNill", "" + String.valueOf(response.body().getCountries().get(0).getId()));
            country.COUNTRYLIST= new ArrayList<String>();
            for (int i = 0; i < response.body().getCountries().size(); i++) {
                country.COUNTRYLIST.add(response.body().getCountries().get(i).getName());
                        }
                      view.setAdapter(country.getCOUNTRYLIST(),countrySpiner);
        }

        @Override
        public void onFailure(Call<Country> call, Throwable t) {
            Log.i("TAG", "onFailure: " + t.getMessage());
            Toast.makeText(context, "Check connection", Toast.LENGTH_SHORT).show();

        }
    });

    }
    public  void   cityAPI(final MaterialBetterSpinner citySpinner,String country) {
        String country_id=null;
        switch (country) {
            case "Egypt":
                country_id = "1";
                view.showFlag(R.drawable.egypt_flag);
                break;
            case "Saudi Arabia":
                country_id = "2";
                view.showFlag(R.drawable.ksa_flag);
                break;

            default: country_id = "0";
        }
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<City> callFile = apiInterface.CityRegion(country_id);

        callFile.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                //Log.i("Resopns_notNill", "statusresponsecity:  " + String.valueOf(response.body().getCities().get(1).getName()));
                if(response.body()!=null&&String.valueOf(response.body().getStatus())=="true"){
                    city.CITYLIST= new ArrayList<String>();
                    for (int i = 0; i < response.body().getCities().size(); i++) {
                        city.CITYLIST.add(response.body().getCities().get(i).getName());
                    }
                    view.setAdapter(city.getCITYLIST(),citySpinner);
                }

            }


            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Check connection", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void RegionData(){
        //country.setCOUNTRYLIST();
   //  city.setEGYPT_CITYLIST();
     //city.setKSA_CITYLIST();
     district.setDISTRICTLIST_Alex();
     district.setDISTRICTLIST_Cairo();
 }

}
