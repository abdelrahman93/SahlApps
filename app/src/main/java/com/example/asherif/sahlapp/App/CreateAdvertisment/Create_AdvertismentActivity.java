package com.example.asherif.sahlapp.App.CreateAdvertisment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.App.Main.MainActivity;

public class Create_AdvertismentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__advertisment_);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Create_AdvertismentActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
