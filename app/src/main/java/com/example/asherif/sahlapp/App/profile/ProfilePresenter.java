package com.example.asherif.sahlapp.App.profile;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.App.Region.City;
import com.example.asherif.sahlapp.App.base.BasePresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

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
                Uri u = data.getData();
                Log.i("TAG", "Uriu: "+u);


                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                    String path=    getFilePath(context,u);
                        URI_Image.setImage(path);

                        view.setimageprofile(bm);


                            view.showmessage("push ya pacha");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                }}
    }}

  public   void startGallery() {
       view.uploadimage();
    }
   public void senddatatosave(String username, String address, String email, String img){
       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
       Call<ProfileModel> callFile = apiInterface.Profile(username,address,email,img);
        callFile.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.i("TAG", "CustomerInfoonResponse: "+response.body().getCustomerInfo().getName());
                Log.i("TAG", "ProfileModelResponse: "+response.body().getCustomerInfo().getPhone());
                Log.i("TAG", "response.body(): "+response.body());

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
               String image=response.body().getCustomerInfo().getImage();
               view.DisplayProfileDataIfExist(name,phone,address,email,image);

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
    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
