package com.example.asherif.sahlapp.Region.Region;

import java.util.ArrayList;

public class Country {
    private ArrayList<String> COUNTRYLIST ;
//    private String mCountryName;
//    private int mFlagImage;





    public ArrayList<String> getCOUNTRYLIST() {

        return COUNTRYLIST;
    }

    public ArrayList<String> setCOUNTRYLIST() {
        COUNTRYLIST  = new ArrayList<String>();
        COUNTRYLIST.add("Egypt");
        COUNTRYLIST.add("Saudi Arabia");
        return COUNTRYLIST;
    }
}
