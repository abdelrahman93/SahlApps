package com.example.asherif.sahlapp.Region.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("device_id")
    @Expose
    private String device_id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_phone")
    @Expose
    private String user_phone;
    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("verification_code")
    @Expose
    private String verificationCode;
    @SerializedName("phone_error")
    @Expose
    private String phone_error;
    public Boolean getStatus() {
        return status;
    }

    public User(String device_id, String user_phone) {
        this.device_id = device_id;
        this.user_phone = user_phone;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getPhone_error() {
        return phone_error;
    }

    public void setPhone_error(String phone_error) {
        this.phone_error = phone_error;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPhone() {
        return user_phone;
    }

    public void setPhone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
