package com.example.bonfire4.nanganmall;

import android.app.Activity;
import android.os.Handler;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();       // 3 초후 이미지를 닫아버림
            }
        }, 3000);

    }



}
