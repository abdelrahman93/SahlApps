package com.example.asherif.sahlapp.App.profile;

import android.graphics.Bitmap;

import java.io.File;

public interface profileview {
    void uploadimage();
    void setimageprofile(Bitmap bitmapImage);
    void sendprofiledatanetwork();
    void showmessage(String messaage);
    void DisplayProfileDataIfExist(String name,String phone,String address,String email);
    void Logout();
    void NavigateToLogin();
    void ShowMessage();
}
