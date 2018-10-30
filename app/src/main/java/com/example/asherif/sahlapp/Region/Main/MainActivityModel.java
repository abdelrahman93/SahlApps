package com.example.asherif.sahlapp.Region.Main;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MainActivityModel {
    //Get Shared Preferences visitor flag;
    SharedPreferences prefs;
   static String flag;
    MainActivity mainActivity;
    MainActivityModel(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        prefs = mainActivity.getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        flag = prefs.getString("visitor_key", null);

    }

}
