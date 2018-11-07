package com.example.asherif.sahlapp.App.profile;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class URI_Image {
    private static File image;

    public URI_Image() {
    }

    public static File getImage() {
        return image;
    }

    public static void setImage(File image) {
        URI_Image.image = image;
    }
}
