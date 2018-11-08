package com.example.asherif.sahlapp.App.profile;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.Observable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.App.Region.City;
import com.example.asherif.sahlapp.App.base.BasePresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ProfilePresenter extends BasePresenter {
    private profileview view;
    private ProfileActivity context;
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    String verification_code = "";

    public ProfilePresenter() {

    }

    public ProfilePresenter(profileview view, ProfileActivity context) {
        this.view = view;
        this.context = context;
        sharedpreferences = context.getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                Uri u = data.getData();
                Log.i("TAG", "Uriu: " + u);


                Bitmap bm = null;
                if (data != null) {
                    try {
                  bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                        String path = getFilePath(context, u);
                        File imageFile = new File(path);
                        Log.i("TAG", "filefilefile: "+imageFile);

              /*          // URI_Image.setImage(path);
                        Log.i("TAG", "pathya basha: " + path);



                  //     File file = FileUtils.getFile(context, u);
                        //pass it like this
                        *//*RequestBody requestFile =
                                RequestBody.create(MediaType.parse("image/*"), imageFile);*//*
                        RequestBody requestFile =
                                RequestBody.create(MediaType.parse(context.getContentResolver().getType(u)), imageFile);
                        Log.i("TAG", "requestFilerequestFile: "+requestFile);



                        // MultipartBody.Part is used to send also the actual file name
                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("image.png", "nnnnnnnnnnnn.png", requestFile);*/


                        URI_Image.setImage(imageFile);

                        view.setimageprofile(bm);


                        view.showmessage("push ya pacha");

                    }  catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public void startGallery() {
        view.uploadimage();
    }

    public void senddatatosave(String username, String address, String email, File img) {
        view.ShowProgressBar();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //get Header api key
        Map<String, String> header = new HashMap<>();
       String api_key = sharedpreferences.getString("Api_key", null);
        Log.i("TAG", "senddatatosave: " + api_key);
        header.put("X-API-Key", String.valueOf(api_key));
        Call<ProfileModel> callFile = apiInterface.Profile(username, address, email,img,header);
        callFile.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                view.HideProgressBar();
                Log.i("TAG", "response.body(): " + response.body());
                Log.i("TAG", "CustomerInfoonResponse: " + response.body().getCustomerInfo().getName());
                Log.i("TAG", "ProfileModelResponse: " + response.body().getCustomerInfo().getImage());
                view.showmessage("success");
                view.NavigateToMain();
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                view.HideProgressBar();

                Log.i("TAG", " t.getMessage().body(): " + t.getMessage());
            }
        });
    }

    public void showmeasage(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

    }

    public void displaydataoncreate() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //get Header api key
        Map<String, String> header = new HashMap<>();
        String api_key = sharedpreferences.getString("Api_key", null);
        Log.i("TAG", "onResponseResendheader: " + api_key);
        header.put("X-API-Key", String.valueOf(api_key));
        Call<ProfileModel> callFile = apiInterface.displayprofile(header);
        callFile.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                view.HideProgressBar();
                Log.i("TAG", "CustomerInfoonResponse: " + response.body().getStatus());
                Log.i("TAG", "ProfileModelResponse: " + response.body().getCustomerInfo().getPhone());
                String name = response.body().getCustomerInfo().getName();
                String phone = response.body().getCustomerInfo().getPhone();
                String address = response.body().getCustomerInfo().getAddress();
                String email = response.body().getCustomerInfo().getEmail();
                String image = response.body().getCustomerInfo().getImage();
                view.DisplayProfileDataIfExist(name, phone, address, email, image);

                view.showmessage("success");
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                view.HideProgressBar();
            }
        });
    }

    public void logoutpresenter(String phone, String deviceID) {
        editor = sharedpreferences.edit();
        editor.putString("verified_user_flag", "false");
        editor.commit();
        view.ShowProgressBar();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LogOutModel> callFile = apiInterface.Logout(phone, deviceID);
        callFile.enqueue(new Callback<LogOutModel>() {
            @Override
            public void onResponse(Call<LogOutModel> call, Response<LogOutModel> response) {
                Log.i("TAG", "onResponse: " + response.body().getStatus());
                view.HideProgressBar();
                if (response.body().getStatus()) {
                    view.NavigateToLogin();
                }
            }

            @Override
            public void onFailure(Call<LogOutModel> call, Throwable t) {
                view.ShowMessage();
                view.HideProgressBar();

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

  /*  public void sendimagetoserver() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.i("TAG", "sendimagetoserver: "+URI_Image.getImage());
        // finally, execute the request
        Call<ProfileModel> call = apiInterface.UpdateImageProfile(URI_Image.getImage());
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.v("Upload", "success");

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }*/
   private String getRealPathFromURI(Uri contentUri) {
       String[] proj = {MediaStore.Images.Media.DATA};
       CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
       Cursor cursor = loader.loadInBackground();
       int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
       cursor.moveToFirst();
       String result = cursor.getString(column_index);
       cursor.close();
       return result;
   }
}
