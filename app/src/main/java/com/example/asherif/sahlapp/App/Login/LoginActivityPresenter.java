package com.example.asherif.sahlapp.App.Login;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
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
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
//push again

    public LoginActivityPresenter(LoginActivity context, LoginView view) {
        this.context = context;
        this.view = view;
    }


    //get values from phone edit text and pass to countryPicker
    public String getPhoneNumber(CountryCodePicker ccp, EditText etPhoneNumber) {
        String number = etPhoneNumber.getText().toString();
        String phoneNumber = ccp.getSelectedCountryCode() + number;
        return phoneNumber;
    }

    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
    }


    //change the hint of spinner choose country code
    public void hintCountryNumber(CountryCodePicker ccp, EditText etPhoneNumber) {


        Log.i("TAG", "hintCountryNumber: " + ccp.getSelectedCountryCode());
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
    public void showChangeLangDialog(CountryCodePicker ccp_lang, String WantchangeLang, String Done, String Cancel) {
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
    public void getCountryLanguage(CountryCodePicker ccp_lang) {

        switch (ccp_lang.getSelectedCountryNameCode()) {
            case "EG":
            case "SA":
            case "IQ":
            case "KW":
            case "LB":
            case "DZ":
                changeLang(context, "ar");
                Toast.makeText(context, "Language changed to Arabic", Toast.LENGTH_SHORT).show();
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

    //for Login API to send verification code
    public void sendVerificationCode(String phone, String device_id) {
        view.showProgressBar();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginModel> callFile = apiInterface.Login(phone, device_id);
        callFile.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body() != null) {
                    if (String.valueOf(response.body().getStatus()) == "true") {
                        Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //Shared Preferences to set flag verification code and api key
                        sharedpreferences = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                        editor = sharedpreferences.edit();
                        editor.putString("verificationCode_key", String.valueOf(response.body().getVerificationCode()));
                        editor.putString("Api_key", String.valueOf(response.body().getApiKey()));
                        editor.putString("phone_key", String.valueOf(response.body().getPhone()));
                        editor.commit();

                        Log.i("TAG", "onResponseLogin: " + response.body().getVerificationCode());
                        Log.i("TAG", "onResponseLogin: " + response.body().getMessage());
                        Log.i("TAG", "onResponseLogin: " + response.body().getPhone());
                        Log.i("TAG", "onResponseLogin: " + response.body().getApiKey());

                        view.hideProgressBar();
                        view.navigateToVerification();
                    } else {
                        Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        view.hideProgressBar();
                    }
                } else {
                    view.showErrorNumber();
                    view.hideProgressBar();
                }

            }


            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Check connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}