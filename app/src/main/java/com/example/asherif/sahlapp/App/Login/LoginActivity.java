package com.example.asherif.sahlapp.App.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Main.MainActivity;
import com.example.asherif.sahlapp.App.Region.Country;
import com.example.asherif.sahlapp.App.Region.RegionActivity;
import com.example.asherif.sahlapp.App.Region.RegionPresenter;
import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.App.Splash.SplashActivity;
import com.example.asherif.sahlapp.App.base.BaseActivity;
import com.hbb20.CountryCodePicker;

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
    @BindString(R.string.i_am_visitor)
    String mystring;
    @BindString(R.string.Want_change_Lang)
    String WantchangeLang;
    @BindString(R.string.Done)
    String Done;
    @BindString(R.string.Cancel)
    String Cancel;
    @BindString(R.string.error_wrong_number)
    String wrongNumber;
    //Shared Preferences
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @NonNull
    @Override
    protected LoginActivityPresenter createPresenter(@NonNull Context context) {
        return new LoginActivityPresenter(LoginActivity.this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }


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
                showChangeLangDialog(ccp_lang, WantchangeLang, Done, Cancel);
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
        //Change hint when change country code
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                hintCountryNumber(ccp, etPhoneNumber);
            }
        });


    }

    //get the phone number with country code
    @Override
    public String getPhoneNumber(CountryCodePicker ccp, EditText etPhoneNumber) {
        return mPresenter.getPhoneNumber(ccp, etPhoneNumber);
    }

    @Override
    public void showProgressBar() {
        mPresenter.showProgressBar(progressBar);
    }

    @Override
    public void hideProgressBar() {
        mPresenter.hideProgressBar(progressBar);
    }

    @Override
    public void showErrorNumber() {
        etPhoneNumber.setError(wrongNumber);
        etPhoneNumber.requestFocus();
    }

    //Set the hint depend on country
    @Override
    public void hintCountryNumber(CountryCodePicker ccp, EditText etPhoneNumber) {
        mPresenter.hintCountryNumber(ccp, etPhoneNumber);
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

    //Alert Dialog for change language
    @Override
    public void showChangeLangDialog(CountryCodePicker ccp_lang, String WantchangeLang, String Done, String Cancel) {
        mPresenter.showChangeLangDialog(ccp_lang, WantchangeLang, Done, Cancel);
    }

    //When click to Login button
    @OnClick(R.id.btn_Login)
    void Loginbtn(View view) {
        editor.putString("visitor_key", "false");
        editor.commit();
        String phone = getPhoneNumber(ccp, etPhoneNumber);
        //Device token
        String device_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (!etPhoneNumber.getText().toString().isEmpty() && (etPhoneNumber.getText().toString().length() >=8&&etPhoneNumber.getText().toString().length()<11)) {
            mPresenter.sendVerificationCode(phone, device_id);

        } else {
            showErrorNumber();
        }

    }


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





