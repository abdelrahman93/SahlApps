package com.example.asherif.sahlapp.App.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.App.Region.City;
import com.example.asherif.sahlapp.App.base.BasePresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter extends BasePresenter {
    private profileview view;
    private ProfileActivity context;

    public  ProfilePresenter(){

    }
    public  ProfilePresenter(profileview view, ProfileActivity context){
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
   public void senddatatosave(String username,String address,String email){
       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
       Call<ProfileModel> callFile = apiInterface.Profile(username,address,email);
        callFile.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.i("TAG", "CustomerInfoonResponse: "+response.body().getCustomerInfo().getName());
                Log.i("TAG", "ProfileModelResponse: "+response.body().getCustomerInfo().getPhone());
                String username=response.body().getCustomerInfo().getName();
                String phone= response.body().getCustomerInfo().getPhone();
                String address=response.body().getCustomerInfo().getAddress();
                String email=response.body().getCustomerInfo().getEmail();
                view.showmessage("success");
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {

            }
        });
   }
   public void showmeasage(String msg){
       Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

   }
   public void displaydataoncreate(String name,String address,String email,String phone){
       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
       Call<ProfileModel> callFile = apiInterface.Profile(name,address,email);
       callFile.enqueue(new Callback<ProfileModel>() {
           @Override
           public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
               Log.i("TAG", "CustomerInfoonResponse: "+response.body().getCustomerInfo().getName());
               Log.i("TAG", "ProfileModelResponse: "+response.body().getCustomerInfo().getPhone());
               String username=response.body().getCustomerInfo().getName();
               String phone= response.body().getCustomerInfo().getPhone();
               String address=response.body().getCustomerInfo().getAddress();
               String email=response.body().getCustomerInfo().getEmail();
               view.DisplayProfileDataIfExist(username,address,email,phone);
               view.showmessage("success");
           }

           @Override
           public void onFailure(Call<ProfileModel> call, Throwable t) {

           }
       });
   }
}
