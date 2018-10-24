package com.example.asherif.sahlapp.Region.Region;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public interface RegionView {
 void setAdapter(ArrayList<String> list, MaterialBetterSpinner spinner);
 void showProgressBar();
 void showFlag(int img);
 void showSnackBar(String s);
 void hideProgressBar();
void navigateToNewestAds();

}
