package com.example.asherif.sahlapp.Region.profile;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Main.MainActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity implements profileview{
    @BindView(R.id.ivprofilepicture)
    ImageView imageView;
    ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        presenter = new ProfilePresenter(this);
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
        Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    //setting the Image after uploading it from the Gallery
    @Override
    public void setimageprofile(Bitmap bitmapImage) {
        imageView.setImageBitmap(bitmapImage);
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

}
