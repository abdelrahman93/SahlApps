package com.example.asherif.sahlapp.Region.Region;

import java.util.ArrayList;

public class District {

    private ArrayList<String> DISTRICTLIST_Cairo ;
    private ArrayList<String> DISTRICTLIST_Alex ;


    public ArrayList<String> getDISTRICTLIST_Cairo() {
        return DISTRICTLIST_Cairo;
    }

    public void setDISTRICTLIST_Cairo() {
        DISTRICTLIST_Cairo  = new ArrayList<String>();
        DISTRICTLIST_Cairo.add("Maadi");
        DISTRICTLIST_Cairo.add("Dokki");
        DISTRICTLIST_Cairo.add("Zamalek");


        this.DISTRICTLIST_Cairo = DISTRICTLIST_Cairo;
    }

    public ArrayList<String> getDISTRICTLIST_Alex() {
        return DISTRICTLIST_Alex;
    }

    public void setDISTRICTLIST_Alex() {
        DISTRICTLIST_Alex  = new ArrayList<String>();
        DISTRICTLIST_Alex.add("Sidi Gaber");
        DISTRICTLIST_Alex.add("Gleem");
        DISTRICTLIST_Alex.add("Miami");

        this.DISTRICTLIST_Alex = DISTRICTLIST_Alex;
    }
}