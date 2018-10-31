package com.example.asherif.sahlapp.Region.Login;


import android.content.Intent;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Region.RegionActivity;
import com.example.asherif.sahlapp.Region.base.BasePresenter;
import com.goodiebag.pinview.Pinview;

import java.sql.Time;
import java.util.Timer;

public class VerificationActivityPresenter extends BasePresenter {
    VerificationActivity context;
    VerificationView view;
    public VerificationActivityPresenter() {
    }

    public VerificationActivityPresenter(VerificationActivity context, VerificationView view) {
        this.context = context;
        this.view = view;
    }
    //when complete pincode & set timer & set resend
    public void pinCode(final TextView timer, final TextView resend ,Pinview pin,String resendString){
        //setunderline to resed
        SpannableString content = new SpannableString(resendString);
        content.setSpan(new UnderlineSpan(), 0, resendString.length(), 0);
        resend.setText(content);

       pin.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                //Make api calls here or what not
                //String code = pinview.getValue().toString();
               // Toast.makeText(context, ""+code, Toast.LENGTH_SHORT).show();
            }
        });

       /* resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              *//*  firebaseAuth = FirebaseAuth.getInstance();
                user = firebaseAuth.getCurrentUser();
                presenter.resend(user.getPhoneNumber());*//*
             //   showProgressBar();

            }
        });
*/

        //time to show retry button
        new CountDownTimer(60000, 1000) {
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
    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }

    //click to Sign in button, send verification code here
    public void btnSignin(final Pinview pin) {

                String code = pin.getValue().toString();
                if (!code.isEmpty() && code.length() == 6) {
                    //  presenter.verifyCode(code);
                    Toast.makeText(context,code, Toast.LENGTH_SHORT).show();
                }
                view.navigateToMain();
            }
    }

