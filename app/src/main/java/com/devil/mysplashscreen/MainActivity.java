package com.devil.mysplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {
        AirplaneModeChangeReciever apr = new AirplaneModeChangeReciever();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in
                        ,R.anim.fade_out);
                finish();
            }
        },5000);

        IntentFilter filter= new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(apr,filter);
    }

    @Override
    protected void onStart() {
        IntentFilter filter= new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(apr,filter);

        super.onStart();
    }
}