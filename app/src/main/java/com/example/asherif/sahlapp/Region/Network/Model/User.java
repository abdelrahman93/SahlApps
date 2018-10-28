package com.example.asherif.sahlapp.Region.Network.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("scodeField")
   private int scode;

    @SerializedName("nameField")
  private String name;

    @SerializedName("loginResultField")
    private int loginResult;

    public User() {

    }

    public User(int scode, String name, int loginResult) {
        this.scode = scode;
        this.name = name;
        this.loginResult = loginResult;
    }

    public int getScode() {
        return scode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoginResult() {
        return loginResult;
    }

}
