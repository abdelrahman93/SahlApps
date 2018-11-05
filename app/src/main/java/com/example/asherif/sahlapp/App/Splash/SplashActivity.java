package com.example.asherif.sahlapp.App.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Login.LoginActivity;
import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity{
    private Timer timer;
   // private ProgressBar progressBar;
    private int i=0;
    private ImageView imglogo;
        ApiClient apiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Locale.getDefault().getDisplayLanguage().equals("English")){
            Toast.makeText(this, "testEnglish", Toast.LENGTH_SHORT).show();
            apiClient.setBASE_URL("http://sahl-app.com/api/user/");

        }else if(Locale.getDefault().getDisplayLanguage().equals("العربية")){
            Toast.makeText(this, "testArabic", Toast.LENGTH_SHORT).show();
            apiClient.setBASE_URL("http://sahl-app.com/ar/api/user/");


        }
       // apiClient.setBASE_URL("http://sahl-app.com/api/user/");

        //  imglogo =findViewById(R.id.imgLogo);
        //imglogo.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_animation));
        //progressBar=(ProgressBar)findViewById(R.id.progressBar);
        //progressBar.setProgress(0);
       /*  progressBar = (DottedProgressBar) findViewById(R.id.progress);
        progressBar.startProgress();
*/
       final long period = 30;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //  textView.setText(String.valueOf(i)+"%");
                        }
                    });
          //          progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(SplashActivity.this,LoginActivity
                            .class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, 0, period);
    }

}