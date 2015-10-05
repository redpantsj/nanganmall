package com.example.bonfire4.nanganmall;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class Prog extends Activity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog);
/*
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);


*/
        final String mURL_seoul = "http://redphant.godohosting.com/so";
        final String mURL_ggn = "http://redphant.godohosting.com/ggnorth";
        final String mURL_ggs = "http://redphant.godohosting.com/ggsouth";
        final String mURL_gb = "http://redphant.godohosting.com/gb";
        final String mURL_gn = "http://redphant.godohosting.com/gn";
        final String mURL_bs = "http://redphant.godohosting.com/bs";



        ImageButton btnCallhome = (ImageButton)findViewById(R.id.imageButton9);
        btnCallhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick", "Callhome");
                onBackPressed();
            }
        });

        Button btnCallseoul = (Button)findViewById(R.id.button_seoul);
        btnCallseoul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(Prog.this, Localprog.class);
                //intent.putExtra("mURL",mURL_seoul);
                Toast.makeText(
                        getApplicationContext(),
                        "현재 해당 대리점 준비중입니다.",
                        Toast.LENGTH_LONG).show();


                //startActivity(intent);


            }
        });

        Button btnCallggn = (Button)findViewById(R.id.button_ggn);
        btnCallggn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Prog.this, Localprog.class);
                intent.putExtra("mURL",mURL_ggn);



                startActivity(intent);


            }
        });

        Button btnCallggs = (Button)findViewById(R.id.button_ggs);
        btnCallggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Prog.this, Localprog.class);
                intent.putExtra("mURL",mURL_ggs);



                startActivity(intent);


            }
        });

        Button btnCalldj = (Button)findViewById(R.id.button_dj);
        btnCalldj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(
                        getApplicationContext(),
                        "현재 해당 대리점 준비중입니다.",
                        Toast.LENGTH_LONG).show();


            }
        });

        Button btnCallgn = (Button)findViewById(R.id.button_gn);
        btnCallgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Prog.this, Localprog.class);
                intent.putExtra("mURL",mURL_gn);



                startActivity(intent);


            }
        });

        Button btnCallbs = (Button)findViewById(R.id.button_bs);
        btnCallbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Prog.this, Localprog.class);
                intent.putExtra("mURL",mURL_bs);



                startActivity(intent);


            }
        });

        Button btnCallgb = (Button)findViewById(R.id.button_gb);
        btnCallgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Prog.this, Localprog.class);
                intent.putExtra("mURL",mURL_gb);



                startActivity(intent);


            }
        });



        //WebSettings settings =  mWebView.getSettings();
        //settings.setLoadWithOverviewMode(true);
        //settings.setUseWideViewPort(true);

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_go_shop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_camera:
                startActivity(new Intent(GoShop.this,MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/






}



