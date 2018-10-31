package com.example.asherif.sahlapp.App.Login;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Network.Model.User;
import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.App.Region.Country;
import com.example.asherif.sahlapp.App.base.BasePresenter;
import com.example.asherif.sahlapp.R;
import com.hbb20.CountryCodePicker;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter extends BasePresenter {
    LoginActivity context;
    LoginView view;
    ApiInterface apiInterface;
    private Country country;


    public LoginActivityPresenter(LoginActivity context, LoginView view,Country country) {
        this.context = context;
        this.view = view;
        this.country=country;
    }

    public LoginActivityPresenter() {
    }
    //get values from phone edit text and pass to countryPicker
    public String getPhoneNumber(CountryCodePicker ccp, EditText etPhoneNumber) {
       ccp.registerCarrierNumberEditText(etPhoneNumber);
        String number = etPhoneNumber.getText().toString();
        String phoneNumber = ccp.getSelectedCountryCodeWithPlus() + number;
        return phoneNumber;
    }

    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    //change the hint of spinner choose country code
    public void hintCountryNumber(CountryCodePicker ccp,EditText etPhoneNumber) {

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


    //change the language of the application
    public void changeLang(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        view.navigateToSplash();
    }

//function of alert dialog when choose translate icon
    public void showChangeLangDialog(CountryCodePicker ccp_lang,String WantchangeLang,String Done,String Cancel ) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.change_language, null);
        dialogBuilder.setView(dialogView);
         ccp_lang = dialogView.findViewById(R.id.ccp_lang);
        dialogBuilder.setTitle(WantchangeLang);

        final CountryCodePicker finalCcp_lang1 = ccp_lang;
        dialogBuilder.setPositiveButton(Done, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                getCountryLanguage(finalCcp_lang1);
            }
        });


        dialogBuilder.setNegativeButton(Cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        AlertDialog b = dialogBuilder.create();
        b.show();

    }

//Choose the language depened on language choosen
    public void getCountryLanguage(CountryCodePicker ccp_lang ) {

        switch (ccp_lang.getSelectedCountryNameCode()) {
            case "EG":
            case "SA":
            case "IQ":
            case "KW":
            case "LB":
                changeLang(context, "ar");
                Toast.makeText(context, "Language changed to arabic", Toast.LENGTH_SHORT).show();
                break;
            case "US":
            case "AU":
            case "GB":
                changeLang(context, "en");
                Toast.makeText(context, "Language changed to English", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "Soon", Toast.LENGTH_SHORT).show();
        }

    }
    public void sendretrfoit(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        User user=new User("966556717622","123456789");
        Call<User> callFile = apiInterface.Login(user);
        callFile.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("Resopns_notNill", "statusresponse"+String.valueOf(response.body().getStatus()));
                Log.i("Resopns_notNill", "phoneerrorresponse"+String.valueOf(response.body().getPhoneError()));


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("TAG", "onFailure: "+t.getMessage());
            }
        });



    }


}