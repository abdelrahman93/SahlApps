package com.example.asherif.sahlapp.App.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("verification_code")
    @Expose
    private String verificationCode;
    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("phone_error")
    @Expose
    private String phoneError;
    @SerializedName("device_id_error")
    @Expose
    private String deviceIdError;

    public Boolean getStatus() {
        return status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getDeviceIdError() {
        return deviceIdError;
    }

    public void setDeviceIdError(String deviceIdError) {
        this.deviceIdError = deviceIdError;
    }

}
