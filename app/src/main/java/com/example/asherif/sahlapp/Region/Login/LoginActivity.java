package com.example.asherif.sahlapp.Region.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.base.BaseActivity;
import com.rilixtech.CountryCodePicker;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginActivityPresenter> implements LoginView {
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.btn_Login)
    Button btnLogin;
    @BindView(R.id.pgloading)
    ProgressBar progressBar;
    @BindView(R.id.visitor_tv)
    TextView IamVistor;
    LoginActivityPresenter presenter;
    @BindString(R.string.i_am_visitor) String mystring;

    // private PhoneAuthModel phoneAuthModel;

    @NonNull
    @Override
    protected LoginActivityPresenter createPresenter(@NonNull Context context) {
        return new LoginActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
        // hideProgressBar();

    }

    private void init() {
        //Set underline to visitor text

        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        IamVistor.setText(content);
        // presenter=new LoginActivityPresenter(this);
        // phoneAuthModel=new PhoneAuthModel();
    }

    //get the phone number with country code
    @Override
    public String getPhoneNumber() {
        //get values from phone edit text and pass to countryPicker
        ccp.registerPhoneNumberTextView(etPhoneNumber);
        String number = etPhoneNumber.getText().toString();
        String phoneNumber = ccp.getSelectedCountryCodeWithPlus() + number;
        return phoneNumber;
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    //Set the hint depend on country
    @Override
    public void hintCountryNumber() {

        switch (ccp.getSelectedCountryCode()) {
            case "20":
                etPhoneNumber.setHint("100 739 8004");
                break;
            case "966":
                etPhoneNumber.setHint("51 234 5678");
                break;
            case "971":
                etPhoneNumber.setHint("50 123 5678");
                break;
            default:
                etPhoneNumber.setHint("");
        }
    }

    //When click to Login button
    @OnClick(R.id.btn_Login)
    void Loginbtn(View view){
        Intent Verificationintent = new Intent(LoginActivity.this, VerificationActivity.class);
        startActivity(Verificationintent);
        finish();
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

    }



