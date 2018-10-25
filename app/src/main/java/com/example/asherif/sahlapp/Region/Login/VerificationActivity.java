package com.example.asherif.sahlapp.Region.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Region.RegionActivity;
import com.example.asherif.sahlapp.Region.base.BaseActivity;
import com.example.asherif.sahlapp.Region.base.BasePresenter;
import com.goodiebag.pinview.Pinview;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VerificationActivity extends BaseActivity implements VerificationView {
    VerificationActivityPresenter presenter;
    public static final String TAG = "VerificationActivity";
    @BindView(R.id.btn_signin)
    Button btn_signin;
    @BindView(R.id.timer)
    TextView timer;
    @BindView(R.id.resend)
    TextView resend;
    @BindView(R.id.pinview)
    Pinview pin;
    @BindView(R.id.pgloadingVerification)
    ProgressBar progressBar;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @NonNull
    @Override
    protected BasePresenter createPresenter(@NonNull Context context) {
        return new VerificationActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        init();
//        hideProgressBar();
        pincode();


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  String code = pin.getValue().toString();
                Intent i = new Intent(VerificationActivity.this, RegionActivity.class);
                startActivity(i);
                finish();
               /* if (!code.isEmpty() && code.length() == 6) {
                    //  presenter.verifyCode(code);

                    return;
                }*/
            }
        });


    }

    private void init() {

        pin = new Pinview(this);

        String mystring = new String("Resend");
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        resend.setText(content);
        /*mAuth = FirebaseAuth.getInstance();
        presenter=new VerificationActivityPresenter(this);
        phoneAuthModel=new PhoneAuthModel();
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);*/
        // editor = pref.edit();


    }

    @Override
    public void pincode() {

     /*   pin.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                //Make api calls here or what not
            }
        });
*/
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
                resend.startAnimation(AnimationUtils.loadAnimation(VerificationActivity.this, R.anim.slide_from_right));
                resend.setVisibility(View.VISIBLE);
            }
        }.start();
        //timer ends here


    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }


}

