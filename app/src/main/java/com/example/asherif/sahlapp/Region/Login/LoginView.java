package com.example.asherif.sahlapp.Region.Login;

import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public interface LoginView {
    String getPhoneNumber(CountryCodePicker ccp, EditText etPhoneNumber);
    void showProgressBar(ProgressBar progressBar);
    void hideProgressBar(ProgressBar progressBar);
    void hintCountryNumber(CountryCodePicker ccp, EditText etPhoneNumber);
    void navigateToVerification();
    void navigateToSplash();
    void navigateToMain();
    void showChangeLangDialog(CountryCodePicker ccp_lang, String WantchangeLang, String Done, String Cancel);
}
