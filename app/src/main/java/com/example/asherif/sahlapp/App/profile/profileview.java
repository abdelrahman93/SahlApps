package com.example.asherif.sahlapp.App.profile;

import android.graphics.Bitmap;

public interface profileview {
    void uploadimage();
    void setimageprofile(Bitmap bitmapImage);
    void sendprofiledatanetwork();
    void showmessage(String messaage);
    void DisplayProfileDataIfExist(String name,String address,String email,String phone);
}
