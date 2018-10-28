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
    MainView view;

    ApiInterface apiService;

    public MainActivityPresenter(MainView view, File file, FileContent fileContent, User user) {
        this.view = view;
        this.file = file;
        this.fileContent = fileContent;
        this.user = user;
    }

    void MainActivity() {

    }
    public void checkVisitor(){
        view.checkVisitor();
    }


}
