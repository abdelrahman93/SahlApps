package com.example.asherif.sahlapp.App.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    EditText etphone;




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

    @Override
    public void sendprofiledatanetwork() {
        String username = etusername.getText().toString();
        String address=etaddress.getText().toString();
        String email=etemail.getText().toString();
        mPresenter.senddatatosave( username, address, email);



    }

    @Override
    public void showmessage(String messaage) {
        mPresenter.showmeasage(messaage);
    }

    @Override
    public void DisplayProfileDataIfExist(String name,String address,String email,String phone) {
    etusername.setText(name);
    etaddress.setText(address);
    etemail.setText(email);
    etphone.setText(phone);
      //.  mPresenter.displaydataoncreate();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);

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
                        presenter.startGallery();
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
    public void savebtn(){
        sendprofiledatanetwork();
    }

}
