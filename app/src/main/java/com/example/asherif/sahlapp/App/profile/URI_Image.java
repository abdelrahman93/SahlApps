package com.example.asherif.sahlapp.App.profile;

import android.net.Uri;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class URI_Image {
    private static Uri image;

    public URI_Image() {
    }

    public static Uri getImage() {
        return image;
    }

    public static void setImage(Uri image) {
        URI_Image.image = image;
    }
}
