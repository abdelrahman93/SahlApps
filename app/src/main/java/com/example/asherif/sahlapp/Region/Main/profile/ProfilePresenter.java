package com.example.asherif.sahlapp.Region.Main.profile;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.asherif.sahlapp.Region.base.BasePresenter;

public class ProfilePresenter extends BasePresenter {
    private profileview view;

    public  ProfilePresenter(){

    }
    public  ProfilePresenter(profileview view){
        this.view=view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

  public   void startGallery() {
       view.uploadimage();
    }
}
