package com.example.asherif.sahlapp.App.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class ProfileModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("customer_info")
    @Expose
    private CustomerInfo customerInfo;
    @SerializedName("debuging")
    @Expose
    private List<Object> debuging = null;
    @SerializedName("debuging_files")
    @Expose
    private List<Object> debugingFiles = null;

    public List<Object> getDebuging() {
        return debuging;
    }

    public void setDebuging(List<Object> debuging) {
        this.debuging = debuging;
    }

    public List<Object> getDebugingFiles() {
        return debugingFiles;
    }

    public void setDebugingFiles(List<Object> debugingFiles) {
        this.debugingFiles = debugingFiles;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

}
