package com.example.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.notesapp.R;
import com.example.notesapp.data.SharedPref;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (SharedPref.isLogin((this))) {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, RegisterActivity.class));
                    finish();
                }
            }, 3000);
        }



        }




    }




