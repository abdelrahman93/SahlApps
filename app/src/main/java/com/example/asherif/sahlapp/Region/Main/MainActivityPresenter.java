package com.example.asherif.sahlapp.Region.Main;

import com.example.asherif.sahlapp.Region.Network.Model.File;
import com.example.asherif.sahlapp.Region.Network.Model.FileContent;
import com.example.asherif.sahlapp.Region.Network.Model.User;
import com.example.asherif.sahlapp.Region.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.Region.base.BasePresenter;

public class MainActivityPresenter extends BasePresenter {
    private File file;
    private FileContent fileContent;
    private User user;

    ApiInterface apiService;

    public MainActivityPresenter(MainActivity mainActivity, File file, FileContent fileContent, User user) {
    }

    void MainActivity() {

    }


}
