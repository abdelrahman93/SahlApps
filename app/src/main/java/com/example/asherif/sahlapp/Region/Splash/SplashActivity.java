package com.example.asherif.sahlapp.Region.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Login.LoginActivity;
import com.trncic.library.DottedProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity{
    private Timer timer;
   // private ProgressBar progressBar;
    private int i=0;
    private ImageView imglogo;
    DottedProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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