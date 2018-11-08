package com.example.asherif.sahlapp.App.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.antonzorin.dottedprogressbar.DottedProgressBar;
import com.example.asherif.sahlapp.App.Login.LoginActivity;
import com.example.asherif.sahlapp.App.Login.LoginActivityPresenter;
import com.example.asherif.sahlapp.App.Main.MainActivity;
import com.example.asherif.sahlapp.App.base.BaseActivity;
import com.example.asherif.sahlapp.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity<ProfilePresenter> implements profileview {
    @BindView(R.id.ivprofilepicture)
    ImageView imageView;
    ProfilePresenter presenter;
    @BindView(R.id.etusername)
    EditText etusername;
    @BindView(R.id.etaddress)
    EditText etaddress;
    @BindView(R.id.et_email)
    EditText etemail;
    @BindView(R.id.et_phone)
    TextView etphone;
    @BindView(R.id.dottedpg)
    DottedProgressBar progressBar;


    @NonNull
    @Override
    protected ProfilePresenter createPresenter(@NonNull Context context) {
        return new ProfilePresenter(this, ProfileActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        presenter = new ProfilePresenter(this, ProfileActivity.this);
        mPresenter.displaydataoncreate();

        etemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isEmailValid(etemail.getText().toString())){
                    email = etemail.getText().toString();
                    flag=false;
                }
                else if(etemail.getText().toString().isEmpty()){
                     email="";
                        flag=false;
                    }
                   else if(!isEmailValid(etemail.getText().toString())){
                    etemail.setError("Enter valid mail");
                        flag=true;


                        }
            }
        });
    }

    @Override
    public void uploadimage() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(ProfileActivity.this.getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //setting the Image after uploading it from the Gallery
    @Override
    public void setimageprofile(Bitmap bitmapImage) {
        imageView.setImageBitmap(bitmapImage);

    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    String email = "";
    boolean flag=false;


    @Override
    public void sendprofiledatanetwork() {
        String username = "";
        String address = "";

        if (!etusername.equals(null)) {
            username = etusername.getText().toString();
        }
        if (!etaddress.equals(null)) {
            address = etaddress.getText().toString();
        }






        // String img = URI_Image.getImage();
        // Toast.makeText(this,  URI_Image.getImage().toString(), Toast.LENGTH_SHORT).show();
        File img = URI_Image.getImage();
        if(!flag){
        mPresenter.senddatatosave(username, address, email, img);}

    }

    @Override
    public void showmessage(String messaage) {
        mPresenter.showmeasage(messaage);
    }

    @Override
    public void DisplayProfileDataIfExist(String name, String phone, String address, String email, String image) {
        etusername.setText(name);
        etaddress.setText(address);
        etemail.setText(email);
        etphone.setText(phone);
        Log.i("TAG", "inputinput: " + image);

        Picasso.get().load(image).into(imageView);


    }

    @Override
    public void Logout() {
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
       String device_id = sharedpreferences.getString("device_id", null);
       String phone=sharedpreferences.getString("phone", null);         // getting String
        mPresenter.logoutpresenter(phone, device_id);
    }

    @Override
    public void NavigateToLogin() {
        Intent LoginIntent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
        finish();
    }

    @Override
    public void ShowMessage() {
        Toast.makeText(this, "Logout Failed Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
    }

    @Override
    public void HideProgressBar() {
        // mCircleProgressBarYellow
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void ShowProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void NavigateToMain() {
        Intent mainintent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(mainintent);
        finish();
    }

    @Override
    public void showSnackBar(String s) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, s, Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      mPresenter.RetryProfile();
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);

    }

    //click listener for Image View
    @OnClick(R.id.ivprofilepicture)
    public void ImageClick() {

        Dexter.withActivity(ProfileActivity.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted, open the Gallery
                        mPresenter.startGallery();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        2000);
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @OnClick(R.id.btn_save)
    public void savebtn() {
        sendprofiledatanetwork();
        // mPresenter.sendimagetoserver();
    }

    @OnClick(R.id.btn_logout)
    public void log_out() {
        Logout();
    }
}
