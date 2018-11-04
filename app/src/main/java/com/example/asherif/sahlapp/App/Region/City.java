package com.example.asherif.sahlapp.App.Region;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class City {

    public ArrayList<String> CITYLIST = new ArrayList<String>();
    @SerializedName("country_id")
    @Expose
    public String country_id;
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("country_id_error")
    @Expose
    private String country_id_error;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("cities")
    @Expose
    private List<City> cities = null;

    public City() {
    }

    public City(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_id_error() {
        return country_id_error;
    }

    public void setCountry_id_error(String country_id_error) {
        this.country_id_error = country_id_error;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<City> getCities() {
        return cities;
    }

    public ArrayList<String> getCITYLIST() {
        return CITYLIST;
    }

    public void setCITYLIST(ArrayList<String> CITYLIST) {
        this.CITYLIST = CITYLIST;
    }
}






