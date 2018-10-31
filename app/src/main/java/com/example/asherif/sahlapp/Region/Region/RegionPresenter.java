package com.example.asherif.sahlapp.Region.Region;

import android.util.Log;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Network.Model.User;
import com.example.asherif.sahlapp.Region.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.Region.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.Region.base.BasePresenter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionPresenter extends BasePresenter {
    private RegionView view;
    private  Country country;
    private City city;
    private District district;
    ApiInterface apiInterface;

    public RegionPresenter() {
    }

    public RegionPresenter(RegionView view, Country country, City city, District district) {
        this.view = view;
        this.country = country;
        this.city = city;
        this.district = district;
    }

public  ArrayList<String> addCountryData(){
    Log.i("TAG", "addCountryData: "+country.getCOUNTRYLIST().get(0));
        return country.getCOUNTRYLIST();
}



    public  ArrayList<String> addCityData(String country){
        ArrayList<String> arr=new ArrayList <String>();
        switch (country){
            case "Egypt":        arr= city.getEGYPT_CITYLIST();
                //sendreuestregion();
            view.showFlag(R.drawable.egypt_flag);
            break;
            case "Saudi Arabia": arr= city.getKSA_CITYLIST();
                view.showFlag(R.drawable.ksa_flag);
                break;
            case "":  // view.showSnackBar();
                break;
            default:


        }
        return arr;

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
    public void AddAdapterPresenterCity(String country,MaterialBetterSpinner spinner){
        view.setAdapter(addCityData(country),spinner);

    }
    public void AddAdapterPresenterDistrict(String city,MaterialBetterSpinner spinner){
        view.setAdapter(addDistrictData(city),spinner);

    }
 public void RegionData(){
      //  country.setCOUNTRYLIST();
     city.setEGYPT_CITYLIST();
     city.setKSA_CITYLIST();
     district.setDISTRICTLIST_Alex();
     district.setDISTRICTLIST_Cairo();
 }
 public  void sendreuestregion(){
     apiInterface = ApiClient.getClient().create(ApiInterface.class);
    com.example.asherif.sahlapp.Region.Network.Model.Country user=new com.example.asherif.sahlapp.Region.Network.Model.Country();
     Call<com.example.asherif.sahlapp.Region.Network.Model.Country > callFile = apiInterface.CountryRegion(user);
   callFile.enqueue(new Callback<com.example.asherif.sahlapp.Region.Network.Model.Country>() {
       @Override
       public void onResponse(Call<com.example.asherif.sahlapp.Region.Network.Model.Country> call, Response<com.example.asherif.sahlapp.Region.Network.Model.Country> response) {
           Log.i("Resopns_notNill", "statusresponse"+String.valueOf(response.body().getStatus()));
           Log.i("Resopns_notNill", "statusresponse"+String.valueOf(response.body().getCountries().get(0).getName()));


       }

       @Override
       public void onFailure(Call<com.example.asherif.sahlapp.Region.Network.Model.Country> call, Throwable t) {
           Log.i("TAG", "onFailure: "+t.getMessage());
       }
   });
 }

}
