package com.usamaqadeer.freshveg.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.usamaqadeer.freshveg.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SystemClock.sleep(1000);
        startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
        finish();
    }
}
