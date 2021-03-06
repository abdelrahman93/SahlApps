package com.example.asherif.sahlapp.App.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asherif.sahlapp.App.Region.RegionActivity;
import com.example.asherif.sahlapp.App.base.BaseActivity;
import com.example.asherif.sahlapp.R;
import com.goodiebag.pinview.Pinview;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VerificationActivity extends BaseActivity<VerificationActivityPresenter> implements VerificationView {
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
    @BindString(R.string.resend)
    String resendString;


    @NonNull
    @Override
    protected VerificationActivityPresenter createPresenter(@NonNull Context context) {
        return new VerificationActivityPresenter(VerificationActivity.this, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        init();
        pinCode();

    }

    private void init() {
        pin = new Pinview(this);
        pin = (Pinview) findViewById(R.id.pinview);
    }

    @OnClick(R.id.btn_signin)
    void Signinbtn(View view) {
        mPresenter.btnSignin(pin);
    }

    @Override
    public void pinCode() {
        mPresenter.pinCode(timer, resend, pin, resendString);
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
    public void navigateToMain() {
        Intent i = new Intent(VerificationActivity.this, RegionActivity.class);
        startActivity(i);
        finish();

    }


}

