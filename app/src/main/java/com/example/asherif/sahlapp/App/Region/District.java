package com.example.asherif.sahlapp.App.Region;

import com.example.asherif.sahlapp.R;

import java.util.ArrayList;

public class District {

    private ArrayList<String> DISTRICTLIST_Cairo ;
    private ArrayList<String> DISTRICTLIST_Alex ;
    private ArrayList<String> DISTRICTLIST_Gaddah ;
    private ArrayList<String> DISTRICTLIST_Alryad ;

    public ArrayList<String> getDISTRICTLIST_Gaddah() {
        return DISTRICTLIST_Gaddah;
    }

    public void setDISTRICTLIST_Gaddah(ArrayList<String> DISTRICTLIST_Gaddah) {
        DISTRICTLIST_Gaddah  = new ArrayList<String>();

        //DISTRICTLIST_Gaddah.add(getString(R.string.lang));
        DISTRICTLIST_Gaddah.add("Dokki");
        DISTRICTLIST_Gaddah.add("Zamalek");
        this.DISTRICTLIST_Gaddah = DISTRICTLIST_Gaddah;
    }

    public ArrayList<String> getDISTRICTLIST_Alryad() {
        return DISTRICTLIST_Alryad;
    }

    public void setDISTRICTLIST_Alryad(ArrayList<String> DISTRICTLIST_Alryad) {
        this.DISTRICTLIST_Alryad = DISTRICTLIST_Alryad;
    }

    public ArrayList<String> getDISTRICTLIST_Cairo() {
        return DISTRICTLIST_Cairo;
    }

    public void setDISTRICTLIST_Cairo() {


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