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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
                      File dsa=  persistImage(bm,"imagetest");
                     // view.sendprofiledatanetwork(dsa);
                        view.setimageprofile(bm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }}
    }}
    public   File persistImage(Bitmap bitmap, String name) {
        File filesDir = context.getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }
        return  imageFile;
    }
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
   public void displaydataoncreate(){
       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
       Call<ProfileModel> callFile = apiInterface.displayprofile();
       callFile.enqueue(new Callback<ProfileModel>() {
           @Override
           public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
               Log.i("TAG", "CustomerInfoonResponse: "+response.body().getStatus());
               Log.i("TAG", "ProfileModelResponse: "+response.body().getCustomerInfo().getPhone());
              String name=response.body().getCustomerInfo().getName();
              String phone=response.body().getCustomerInfo().getPhone();
              String address=response.body().getCustomerInfo().getAddress();
              String email=response.body().getCustomerInfo().getEmail();
               view.DisplayProfileDataIfExist(name,phone,address,email);
               view.showmessage("success");
           }

           @Override
           public void onFailure(Call<ProfileModel> call, Throwable t) {

           }
       });
   }
   public void logoutpresenter(String phone ,String deviceID){
       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
       Call<LogOutModel> callFile = apiInterface.Logout(phone,deviceID);
       callFile.enqueue(new Callback<LogOutModel>() {
           @Override
           public void onResponse(Call<LogOutModel> call, Response<LogOutModel> response) {
               Log.i("TAG", "onResponse: "+response.body().getStatus());
               if(response.body().getStatus()){
                   view.NavigateToLogin();
               }
           }

           @Override
           public void onFailure(Call<LogOutModel> call, Throwable t) {
            view.ShowMessage();
           }
       });
   }
}
