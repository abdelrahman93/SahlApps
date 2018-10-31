package com.example.asherif.sahlapp.Region.Login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Main.MainActivity;
import com.example.asherif.sahlapp.Region.Splash.SplashActivity;
import com.example.asherif.sahlapp.Region.base.BaseActivity;
import com.hbb20.CountryCodePicker;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginActivityPresenter> implements LoginView {
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    CountryCodePicker ccp_lang;
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.btn_Login)
    Button btnLogin;
    @BindView(R.id.pgloading)
    ProgressBar progressBar;
    @BindView(R.id.visitor_tv)
    TextView iamVistor;
    LoginActivityPresenter presenter;
    @BindString(R.string.i_am_visitor)
    String mystring;
    @BindString(R.string.Want_change_Lang)
    String WantchangeLang;
    @BindString(R.string.Done)
    String Done;
    @BindString(R.string.Cancel)
    String Cancel;


    //Shared Preferences to set flag visitor
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    // private PhoneAuthModel phoneAuthModel;

    @NonNull
    @Override
    protected LoginActivityPresenter createPresenter(@NonNull Context context) {
        return new LoginActivityPresenter(LoginActivity.this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();

    }

    // hideProgressBar();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.lang:
                showChangeLangDialog(ccp_lang,WantchangeLang,Done,Cancel);
                break;
        }
        return true;
    }

    private void init() {
        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        //Set underline to visitor text
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        iamVistor.setText(content);
        // presenter=new LoginActivityPresenter(this);
        // phoneAuthModel=new PhoneAuthModel();
    }

    //get the phone number with country code
    @Override
    public String getPhoneNumber(CountryCodePicker ccp, EditText etPhoneNumber) {
        return         mPresenter.getPhoneNumber(ccp,etPhoneNumber);

    }


    @Override
    public void showProgressBar(ProgressBar progressBar) {
        mPresenter.showProgressBar(progressBar);
    }

    @Override
    public void hideProgressBar(ProgressBar progressBar) {
mPresenter.hideProgressBar(progressBar);
    }

    //Set the hint depend on country
    @Override
    public void hintCountryNumber(CountryCodePicker ccp,EditText etPhoneNumber) {
       mPresenter.hintCountryNumber(ccp,etPhoneNumber);
    }

    @Override
    public void navigateToVerification() {
        Intent i = new Intent(getApplicationContext(), VerificationActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void navigateToSplash() {
        Intent i = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void navigateToMain() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showChangeLangDialog(CountryCodePicker ccp_lang,String WantchangeLang,String Done,String Cancel ) {
        mPresenter.showChangeLangDialog(ccp_lang,WantchangeLang,Done,Cancel);
    }

    //When click to Login button
    @OnClick(R.id.btn_Login)
    void Loginbtn(View view) {
        editor.putString("visitor_key", "false");
        editor.commit();
        navigateToVerification();



               /* if(!etPhoneNumber.getText().toString().isEmpty()&&  (etPhoneNumber.getText().toString().length() >= 9)){
                   // presenter.updatePhone(getPhoneNumber());
                    Intent Verificationintent=new Intent(LoginActivity.this,VerificationActivity.class);
                    startActivity(Verificationintent);
                    finish();
                }else{
                    etPhoneNumber.setError("please Enter a Valid Number");
                    etPhoneNumber.requestFocus();

                }*/

    }
        /*});
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                //  hintCountryNumber();
            }
        });*/


    //When click to I am a visitor
    @OnClick(R.id.visitor_tv)
    void clickVisitorText(View v) {
        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString("visitor_key", "true");
        editor.commit();
        navigateToMain();
    }





}





