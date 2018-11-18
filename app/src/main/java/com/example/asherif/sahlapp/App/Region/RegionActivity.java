package com.example.asherif.sahlapp.App.Region;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.antonzorin.dottedprogressbar.DottedProgressBar;
import com.example.asherif.sahlapp.App.Login.LoginActivity;
import com.example.asherif.sahlapp.App.Main.MainActivity;
import com.example.asherif.sahlapp.App.Network.Model.User;
import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.App.base.BaseActivity;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionActivity extends BaseActivity<RegionPresenter> implements RegionView {

    @BindView(R.id.sp_country)
    MaterialBetterSpinner countrySpinner;
    @BindView(R.id.sp_city)
    MaterialBetterSpinner citySpinner;
    @BindView(R.id.sp_district)
    MaterialBetterSpinner districtSpinner;
    @BindView(R.id.btnsearch)
    Button btnsearch;
    @BindView(R.id.tregion)
    TextView t_region;
    @BindView(R.id.countrylogo)
    ImageView countryImage;
    @BindView(R.id.pgloadingRegion)
    DottedProgressBar progressBar;
    private String country = "";
    //Shared Preferences
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @NonNull
    @Override
    protected RegionPresenter createPresenter(@NonNull Context context) {
        return new RegionPresenter(RegionActivity.this, RegionActivity.this, new Country(), new City(), new District());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        ButterKnife.bind(this);
        checkOTP();
        showProgressBar();
        //Set default adapter before API
        mPresenter.AddAdapterPresenterDefault(countrySpinner, citySpinner, districtSpinner);
        //Set country Data to country spinner
        mPresenter.countryAPI(countrySpinner);
        init();
        //


//Incase connection down when choose set adapter again
countrySpinner.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(mPresenter.arrayCountry==null){
            Log.i("TAG", "testspinnercountry"+"hello");
            mPresenter.countryAPI(countrySpinner);
        }
    }
});

//on click spinners , set adapter depend on choosen country
        countrySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ArrayList<String> arr=new ArrayList <String>();
                setAdapter(arr,citySpinner);
                setAdapter(arr,districtSpinner);
                country = adapterView.getItemAtPosition(position).toString();
                showProgressBar();
                mPresenter.cityAPI(citySpinner, country);

                //reset district,city  spinners to default
                citySpinner.setText("");
                districtSpinner.setText("");
            }
        });


        citySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city = adapterView.getItemAtPosition(i).toString();
                mPresenter.AddAdapterPresenterDistrict(city, districtSpinner);
                //reset district spinner to default
                districtSpinner.setText("");


            }
        });


    }


    //Initialization
    public void init() {
        mPresenter.RegionData();
    }

    public void checkOTP() {
        sharedpreferences = getApplicationContext().getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        String keyOTP = sharedpreferences.getString("verified_user_flag", "false");
        if (!keyOTP.equals("true")) {
            Intent loginintent = new Intent(RegionActivity.this, LoginActivity.class);
            startActivity(loginintent);
            finish();
        }
    }

    //init the adapter in general
    @Override
    public void setAdapter(ArrayList<String> list, MaterialBetterSpinner spinner) {
        ArrayAdapter<String> arrayAdapterCountry = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(arrayAdapterCountry);
    }


    //show flag when choose country
    @Override
    public void showFlag(int image) {
        if(image==0){
          countryImage.setVisibility(View.GONE);
        }
        else{
            countryImage.setImageResource(image);
            t_region.setVisibility(View.GONE);
            countryImage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_animation));
        }

    }

    @Override
    public void showSnackBar(String s) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, s, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }


    //When click to Search button
    @OnClick(R.id.btnsearch)
    void searchButton(View view) {
        //Validation to Search button to check choose all region spinners
        if (!countrySpinner.getText().toString().equals("") && !citySpinner.getText().toString().equals("")) {
            navigateToMainmenu();
        } else {
            //set msg of snackbar for error region
            showSnackBar(getString(R.string.error_region_msg));
        }
    }

    @Override
    public void showProgressBar() {
        mPresenter.showProgressBar(progressBar);
    }

    @Override
    public void hideProgressBar() {
        mPresenter.hideProgressBar(progressBar);

    }

    @Override
    public void navigateToMainmenu() {
        Intent i = new Intent(RegionActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
