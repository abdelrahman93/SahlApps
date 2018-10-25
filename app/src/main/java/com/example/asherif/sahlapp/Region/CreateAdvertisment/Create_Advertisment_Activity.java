package com.example.asherif.sahlapp.Region.CreateAdvertisment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Main.MainActivity;
import com.example.asherif.sahlapp.Region.profile.ProfileActivity;

public class Create_Advertisment_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__advertisment_);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Create_Advertisment_Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
