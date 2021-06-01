package com.car_pa_ra.overgarage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        openApp(true);

        ImageView icono = (ImageView) findViewById(R.id.imgApp);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fadeln);
        icono.startAnimation(anim);

        openApp(true);
    }

    private void openApp(boolean localPermission){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity
                        .this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }
}