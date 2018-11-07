package com.example.asherif.sahlapp.App.profile;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.net.URL;

public interface profileview {
    void uploadimage();
    void setimageprofile(Bitmap bitmapImage);
    void sendprofiledatanetwork();
    void showmessage(String messaage);
    void DisplayProfileDataIfExist(String name,String phone,String address,String email,String image);
    void Logout();
    void NavigateToLogin();
    void ShowMessage();
    void  HideProgressBar();
    void  ShowProgressBar();
    void  NavigateToMain();
}
