package com.example.bonfire4.nanganmall;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity {









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this,splash.class));

/*
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        */

/*
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            Save_Path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + Save_folder;
        }
        */



        ImageButton btnCallCatal = (ImageButton)findViewById(R.id.imageButton1);
        btnCallCatal.setOnClickListener(new View.OnClickListener() {

            /*
            @Override
            public void onClick(View v) {
                Log.i("OnClick","Callpdf");

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.addCategory(intent.CATEGORY_DEFAULT);
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"nangan.pdf")), "application/pdf");

                try {
                    startActivity(intent);
                } catch(Exception e) {
                    Log.e("FileManager : ", e.getMessage());
                    Toast.makeText(
                            getApplicationContext(),
                            "pdf파일 뷰어를 설치해 주세요.",
                            Toast.LENGTH_LONG).show();
                }


*/
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.i("OnClick","Ccatal");
                Intent intentSubActivity = new Intent(MainActivity.this,Ccatal.class);
                startActivity(intentSubActivity);



            }





        });

        ImageButton btnCallNoti = (ImageButton)findViewById(R.id.imageButton4);
        btnCallNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick","CallNoti");
                Intent intentSubActivity = new Intent(MainActivity.this,Noti.class);
                startActivity(intentSubActivity);
            }
        });

        ImageButton btnCallProg = (ImageButton)findViewById(R.id.imageButton5);
        btnCallProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick","CallProg");
                Intent intentSubActivity = new Intent(MainActivity.this,GoShop.class);
                startActivity(intentSubActivity);
            }
        });


        ImageButton btnCallGoshop = (ImageButton)findViewById(R.id.imageButton3);
        btnCallGoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick","CallGoshop");
                Intent intentSubActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nanganmall.com"));

                startActivity(intentSubActivity);
                //Intent intentSubActivity = new Intent(MainActivity.this,GoShop.class);
                //startActivity(intentSubActivity);
            }
        });

        ImageButton btnCallmap = (ImageButton)findViewById(R.id.imageButton2);
        btnCallmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick","Callmap");
                Intent intentSubActivity = new Intent(MainActivity.this,map.class);
                startActivity(intentSubActivity);
            }
        });

        ImageButton btnCallpic = (ImageButton)findViewById(R.id.imageButton6);
        btnCallpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick","Callpic");
                Intent intentSubActivity = new Intent(MainActivity.this,Picgo.class);
                startActivity(intentSubActivity);
            }
        });




    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_camera:
                startActivity(new Intent(MainActivity.this,GoShop.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/











}



