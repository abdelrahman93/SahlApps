package com.example.asherif.sahlapp.App.Login;

import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public interface LoginView {
    String getPhoneNumber(CountryCodePicker ccp, EditText etPhoneNumber);
    void showProgressBar();
    void hideProgressBar();
    void showErrorNumber();
    void hintCountryNumber(CountryCodePicker ccp, EditText etPhoneNumber);
    void navigateToVerification();
    void navigateToSplash();
    void navigateToMain();
    void showChangeLangDialog(CountryCodePicker ccp_lang, String WantchangeLang, String Done, String Cancel);
}
