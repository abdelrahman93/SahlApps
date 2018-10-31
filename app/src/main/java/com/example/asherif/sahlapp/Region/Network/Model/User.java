package com.example.asherif.sahlapp.Region.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("phone_error")
    @Expose
    private String phoneError;
    @SerializedName("user_phone")
    @Expose
    private String user_phone;
    @SerializedName("device_id")
    @Expose
    private String device_id;

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public User(String user_phone, String device_id) {
        this.user_phone = user_phone;
        this.device_id = device_id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

}
