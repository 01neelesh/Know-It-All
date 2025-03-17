package com.example.triviaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // 3 seconds
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent intent = new Intent(splash_Screen.this.peekAvailableContext(),MainActivity.class);
                       startActivity(intent);
                       finish();
                   }
               }, SPLASH_TIME_OUT);
            }
        }

