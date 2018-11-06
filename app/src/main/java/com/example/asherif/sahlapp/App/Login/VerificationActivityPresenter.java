package com.example.asherif.sahlapp.App.Login;


import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Network.Rest.ApiInterface;
import com.example.asherif.sahlapp.App.base.BasePresenter;
import com.example.asherif.sahlapp.R;
import com.goodiebag.pinview.Pinview;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class VerificationActivityPresenter extends BasePresenter {
    VerificationActivity context;
    VerificationView view;
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    String verification_code = "";
    String api_key = "";
    String phone = "";

    public VerificationActivityPresenter() {
    }

    public VerificationActivityPresenter(VerificationActivity context, VerificationView view) {
        this.context = context;
        this.view = view;
        sharedpreferences = context.getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
    }

    //When complete pincode & set timer & set resend
    public void pinCode(final TextView timer, final TextView resend, Pinview pin, String resendString) {
        //set underline to resend
        SpannableString content = new SpannableString(resendString);
        content.setSpan(new UnderlineSpan(), 0, resendString.length(), 0);
        resend.setText(content);

       /* pin.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                //
            }
        });*/

        showTimer(timer, resend);

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendCode();
                showTimer(timer, resend);
            }
        });


    }

    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    //function to count timer
    public void showTimer(final TextView timer, final TextView resend) {
        //time to show retry button
        new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("0:" + l / 1000 + " s");
                resend.setVisibility(View.GONE);
            }

            @Override
            public void onFinish() {
                timer.setText(0 + " s");
                resend.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_from_right));
                resend.setVisibility(View.VISIBLE);
            }
        }.start();
        //timer ends here

    }

    //for Resend API
    public void resendCode() {
        view.showProgressBar();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Map<String, String> header = new HashMap<>();
        api_key = sharedpreferences.getString("Api_key", null);
        phone = sharedpreferences.getString("phone_key", null);
        Log.i("TAG", "onResponseResendheader: " + api_key);
        header.put("X-API-Key", String.valueOf(api_key));
        Call<LoginModel> callFile = apiInterface.Resend(phone,header);
        callFile.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body() != null) {
                if (String.valueOf(response.body().getStatus()) == "true") {
                    Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    //Shared Preferences to set verification code and api key
                    editor = sharedpreferences.edit();
                    editor.putString("verificationCode_key", String.valueOf(response.body().getVerificationCode()));
                    editor.putString("Api_key", String.valueOf(response.body().getApiKey()));
                    editor.commit();

                    Log.i("TAG", "onResponseResend: " + response.body().getVerificationCode());
                    Log.i("TAG", "onResponseResend: " + response.body().getMessage());
                    Log.i("TAG", "onResponseResend: " + response.body().getPhone());
                    view.hideProgressBar();
                } else {
                    Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Check connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }


    //click to Sign in button, check for verification code is correct or not
    public void btnSignin(final Pinview pin) {

        String code = pin.getValue().toString();
        if (!code.isEmpty() && code.length() == 4) {
            verification_code = sharedpreferences.getString("verificationCode_key", null);
            Log.i("TAG", "btnSignin: " + verification_code);
            if (code.equals(verification_code)) {
                //Write a flag for verified user to (One Time Password) and check it in Splash
                editor = sharedpreferences.edit();
                editor.putString("verified_user_flag", "true");
                editor.commit();
                Toast.makeText(context, "Verified Successfully", Toast.LENGTH_SHORT).show();
                view.navigateToMain();
            } else {
                Toast.makeText(context, ""+R.string.error_wrong_verification, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

