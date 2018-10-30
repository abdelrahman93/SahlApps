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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String country = "";

    @NonNull
    @Override
    protected RegionPresenter createPresenter(@NonNull Context context) {
        return new RegionPresenter(this, new Country(), new City(), new District());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        ButterKnife.bind(this);

        init();

        //When click to Search button go to main activity with choosen region
        /*btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validation to Search button to check choose all region spinners
                if (!countrySpinner.getText().toString().equals("") && !citySpinner.getText().toString().equals("") && !districtSpinner.getText().toString().equals("")) {
                    Intent i = new Intent(RegionActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    showSnackBar("Please choose valid region");
                }


            }
        });
*/
        //Set Country Data to country spinner
        setAdapter(mPresenter.addCountryData(), countrySpinner);
        mPresenter.AddAdapterPresenterCity("", citySpinner);
        mPresenter.AddAdapterPresenterDistrict("", districtSpinner);

//on click spinners , set adapter depend on choosen country
        countrySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                country = adapterView.getItemAtPosition(position).toString();
                mPresenter.AddAdapterPresenterCity(country, citySpinner);

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

    @Override
    public void changeHint() {
        citySpinner.setHint("الإمارة");
        districtSpinner.setHint("الحى");

    }

    //When click to Search button
    @OnClick(R.id.btnsearch)
    void searchButton(View view) {
        //Validation to Search button to check choose all region spinners
        if (!countrySpinner.getText().toString().equals("") && !citySpinner.getText().toString().equals("") && !districtSpinner.getText().toString().equals("")) {
            Intent i = new Intent(RegionActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            //set msg of snackbar for error region
            showSnackBar(getString(R.string.error_region_msg));
        }
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
