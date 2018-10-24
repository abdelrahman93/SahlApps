package com.example.asherif.sahlapp.Region.Region;

import java.util.ArrayList;

public class City {

    private ArrayList<String> EGYPT_CITYLIST ;
    private ArrayList<String> KSA_CITYLIST;

    public ArrayList<String> getEGYPT_CITYLIST() {
        EGYPT_CITYLIST = new ArrayList<String>();
        EGYPT_CITYLIST.add("Cairo");
        EGYPT_CITYLIST.add("Alex");
        EGYPT_CITYLIST.add("Giza");
        return EGYPT_CITYLIST;
    }

    public void setEGYPT_CITYLIST() {
        this.EGYPT_CITYLIST = EGYPT_CITYLIST;
    }

    public ArrayList<String> getKSA_CITYLIST() {
        KSA_CITYLIST = new ArrayList<String>();
        KSA_CITYLIST.add("Jeddah");
        KSA_CITYLIST.add("Dammam");
        KSA_CITYLIST.add("Riyadh");
        return KSA_CITYLIST;
    }

    public void setKSA_CITYLIST() {
        this.KSA_CITYLIST = KSA_CITYLIST;
    }
}

