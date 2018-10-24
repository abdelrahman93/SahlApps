package com.example.asherif.sahlapp.Region.Region;

import android.support.annotation.NonNull;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.base.BasePresenter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class RegionPresenter extends BasePresenter {
    private RegionView view;
    private  Country country;
    private City city;
    private District district;

    public RegionPresenter() {
    }

    public RegionPresenter(RegionView view, Country country, City city, District district) {
        this.view = view;
        this.country = country;
        this.city = city;
        this.district = district;
    }

public  ArrayList<String> addCountryData(){

        return country.getCOUNTRYLIST();
}



    public  ArrayList<String> addCityData(String country){
        ArrayList<String> arr=new ArrayList <String>();
        switch (country){
            case "Egypt":        arr= city.getEGYPT_CITYLIST();
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
        country.setCOUNTRYLIST();
     city.setEGYPT_CITYLIST();
     city.setKSA_CITYLIST();
     district.setDISTRICTLIST_Alex();
     district.setDISTRICTLIST_Cairo();
 }

}
