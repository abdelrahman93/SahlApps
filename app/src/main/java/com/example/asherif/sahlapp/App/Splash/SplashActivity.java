package com.example.asherif.sahlapp.App.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Login.LoginActivity;
import com.example.asherif.sahlapp.App.Network.Rest.ApiClient;
import com.example.asherif.sahlapp.App.Region.RegionActivity;
import com.example.asherif.sahlapp.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private Timer timer;
    // private ProgressBar progressBar;
    private int i = 0;
    private ImageView imglogo;
    ApiClient apiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);


        apiClient.setBASE_URL("http://sahl-app.com/api/user/");
        //get phone language then change the Base URL
        String phoneLang = Locale.getDefault().getDisplayLanguage();
        switch (phoneLang) {
            case "English":
                apiClient.setBASE_URL("http://sahl-app.com/api/user/");
                break;
            case "العربية":
                apiClient.setBASE_URL("http://sahl-app.com/ar/api/user/");
                break;
            default:
                apiClient.setBASE_URL("http://sahl-app.com/api/user/");
        }

        //  imglogo =findViewById(R.id.imgLogo);
        //imglogo.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_animation));
        //progressBar=(ProgressBar)findViewById(R.id.progressBar);
        //progressBar.setProgress(0);
       /*  progressBar = (DottedProgressBar) findViewById(R.id.progress);
        progressBar.startProgress();
*/
        final long period = 30;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i < 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //  textView.setText(String.valueOf(i)+"%");
                        }
                    });
                    //          progressBar.setProgress(i);
                    i++;
                } else {
                    //closing the timer
                    timer.cancel();
                        Intent intent = new Intent(SplashActivity.this, RegionActivity
                                .class);
                        startActivity(intent);
                        // close this activity
                        finish();


                }
            }
        }, 0, period);
    }

}