package com.example.asherif.sahlapp.Region.Region;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Main.MainActivity;
import com.example.asherif.sahlapp.Region.base.BaseActivity;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class RegionActivity extends BaseActivity<RegionPresenter> implements RegionView {
    private MaterialBetterSpinner countrySpinner;
    private MaterialBetterSpinner citySpinner;
    private MaterialBetterSpinner districtSpinner;
    private String country = "";
    private Button btnsearch;
    private TextView t_region;
    private ImageView countryImage;

    @NonNull
    @Override
    protected RegionPresenter createPresenter(@NonNull Context context) {
        return new RegionPresenter(this, new Country(), new City(), new District());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        init();
        //When click to Search button go to main activity with choosen region
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegionActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        //Set Country Data to country spinner
        setAdapter(mPresenter.addCountryData(), countrySpinner);
        mPresenter.AddAdapterPresenterCity("", citySpinner);
        mPresenter.AddAdapterPresenterDistrict("", districtSpinner);

//on click spinners
        countrySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                country = adapterView.getItemAtPosition(position).toString();
                mPresenter.AddAdapterPresenterCity(country, citySpinner);
            }
        });


        citySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String city = adapterView.getItemAtPosition(i).toString();

                mPresenter.AddAdapterPresenterDistrict(city, districtSpinner);


            }
        });


    }

    //Initialization
    public void init() {
        countrySpinner = findViewById(R.id.sp_country);
        citySpinner = findViewById(R.id.sp_city);
        districtSpinner = findViewById(R.id.sp_district);
        btnsearch = findViewById(R.id.btnsearch);
        countryImage = findViewById(R.id.countrylogo);
        t_region = findViewById(R.id.tregion);
        mPresenter.RegionData();


    }

    //init the adapter in general
    @Override
    public void setAdapter(ArrayList<String> list, MaterialBetterSpinner spinner) {
        ArrayAdapter<String> arrayAdapterCountry = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(arrayAdapterCountry);
    }

    @Override
    public void showProgressBar() {

    }

    //show flag when choose country
    @Override
    public void showFlag(int image) {
        countryImage.setImageResource(image);
        t_region.setVisibility(View.GONE);
        countryImage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_animation));
    }

    @Override
    public void showSnackBar() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Please Select Country", Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void navigateToNewestAds() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
