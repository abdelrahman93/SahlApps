package com.example.asherif.sahlapp.Region.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Main.MainActivity;
import com.example.asherif.sahlapp.Region.Network.Model.Country;
import com.example.asherif.sahlapp.Region.Network.Model.User;
import com.example.asherif.sahlapp.Region.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.Region.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.Region.Splash.SplashActivity;
import com.example.asherif.sahlapp.Region.base.BaseActivity;
import com.rilixtech.CountryCodePicker;

import java.io.IOException;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    //Shared Preferences to set flag visitor
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor ;
    ApiInterface apiInterface;


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
        Country country=new Country();
        Call<Country> callFile = apiInterface.Country(country);
        callFile.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

              Log.i("TAG", "response.body: "+response.body().getCountries().get(0).getName());
                Log.i("TAG", "responseresponseresponse "+response.body().getId());

            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.i("TAG", "onFailure: "+t.getMessage());

            }
        });


    }

    private void init() {
         sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
         editor = sharedpreferences.edit();
        //Set underline to visitor text
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        IamVistor.setText(content);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

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
    /*    editor.putString("visitor_key", "false");
        editor.commit();
        Intent Verificationintent = new Intent(LoginActivity.this, VerificationActivity.class);
        startActivity(Verificationintent);
        finish();*/
        User user=new User("123456789","966556717622");
        Call<User> callFile = apiInterface.Login(user);
        callFile.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.i("TAG", "onResponseonResponse: "+response.body().getStatus());
               // Log.i("TAG", "response.body(): "+response.body().getStatus());
                Log.i("TAG", "getPhone_error: "+response.body().getPhone_error());



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("TAG", "onFailure: "+t.getMessage());

            }
        });





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
    void clickVisitorText(View v){
         sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

         editor = sharedpreferences.edit();

        editor.putString("visitor_key", "true");
        editor.commit();
        Intent i =new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
    public  void changeLang(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        Toast.makeText(context, "language changed", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(LoginActivity.this, SplashActivity.class);
        startActivity(i);
        finish();
    }
    }





