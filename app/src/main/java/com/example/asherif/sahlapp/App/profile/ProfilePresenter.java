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
import com.example.asherif.sahlapp.App.base.BasePresenter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
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
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String verification_code = "";
    private final String LOG_TAG = "BNK";

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
                URI_Image.setImage(u);
                Log.i("TAG", "Uriu: " + u);


                Bitmap bm = null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                        String path = getFilePath(context, u);
                        File imageFile = new File(u.getPath());
                        Log.i("TAG", "filefilefile: " + u.getPath());


                        view.setimageprofile(bm);


                        view.showmessage("Done");

                    } catch (URISyntaxException e) {
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



    public void showmeasage(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

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
                Log.i("TAG", "onResponsedhelso2aal: " + response.body());
                if (response.body() != null) {
                    Log.i("TAG", "CustomerInfoonResponse: " + response.body().getStatus());
                    Log.i("TAG", "ProfileModelResponse: " + response.body().getCustomerInfo().getPhone());
                    String name = response.body().getCustomerInfo().getName();
                    String phone = response.body().getCustomerInfo().getPhone();
                    String address = response.body().getCustomerInfo().getAddress();
                    String email = response.body().getCustomerInfo().getEmail();
                    String image = response.body().getCustomerInfo().getImage();


                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("phone", phone);  // Saving string
                    editor.putString("image",image);
                    // Save the changes in SharedPreferences
                    editor.commit(); // commit changes

                    view.DisplayProfileDataIfExist(name, phone, address, email, image);
                } else {
                    view.showSnackBar("Bad Connection Please Try Again!");
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {


                view.HideProgressBar();
                view.showmessage("Check connection");
                Log.i("TAG", "t.getMessage(): " + t.getMessage());
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

    File file;

    private void uploadFile(Uri fileUri, String name, String Address, String email) {
        view.ShowProgressBar();

        Log.i("TAG", "uploadFiledsadasd: " + fileUri);
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

        SharedPreferences sharedpreferences = context.getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        String image = sharedpreferences.getString("image", null);
        Log.i("TAG", "uploadFile: "+image);
        if(fileUri==null){
            Uri myUri = Uri.parse(image);
            try {
                file = new File(getFilePath(context,myUri));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Log.i("TAG", "fileyafile: " + file);

            Log.i("TAG", "filefile: " + myUri);}
        else {
             file = FileUtils.getFile(context, fileUri);
            Log.i("TAG", "fileelasly: " + file);

        }

        Log.i("TAG", "knowfile: " + file);
            // create upload service client
            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

                // creates RequestBody instance from file
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // MultipartBody.Part is used to send also the actual filename
                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

                RequestBody username = RequestBody.create(MediaType.parse("multipart/form-data"), name);
                RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), Address);
                RequestBody Email = RequestBody.create(MediaType.parse("multipart/form-data"), email);


                // executes the request
                Call<ResponseBody> call = service.EditProfile(body, username, address, Email);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {
                        view.HideProgressBar();
                        Log.i(LOG_TAG, "response.code" + response.body().toString());


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        view.HideProgressBar();
                        Log.e(LOG_TAG, t.getMessage());
                    }
                });




        // add another part within the multipart request
        String descriptionString = "ok8ogkgw884s8oswcogcggwgwskkosc88gkcgkwk";

        //get Header api key
        Map<String, String> header = new HashMap<>();
        String api_key = sharedpreferences.getString("Api_key", null);
        Log.i("TAG", "onResponseResendheader: " + api_key);
        header.put("X-API-Key", String.valueOf(api_key));


    }

    void SendImageMultiPart(Uri image, String name, String address, String email) {
        Log.i("TAG", "URI_Image.getImage(): " + URI_Image.getImage());
        uploadFile(image, name, address, email);
    }

    public void RetryProfile() {
        view.NavigateToMain();
    }
}
