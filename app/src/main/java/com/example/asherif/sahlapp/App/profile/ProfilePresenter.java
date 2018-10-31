package com.example.asherif.sahlapp.App.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.example.asherif.sahlapp.App.base.BasePresenter;

import java.io.IOException;

public class ProfilePresenter extends BasePresenter {
    private profileview view;
    private Context context;

    public  ProfilePresenter(){

    }
    public  ProfilePresenter(profileview view, Context context){
        this.view=view;
        this.context=context;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==1000){
                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                        view.setimageprofile(bm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }}
    }}

  public   void startGallery() {
       view.uploadimage();
    }
}
