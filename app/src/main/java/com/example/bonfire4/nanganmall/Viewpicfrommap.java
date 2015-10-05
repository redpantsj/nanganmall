package com.example.bonfire4.nanganmall;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Viewpicfrommap extends Activity {

    ImageView pic;
    ImageView pic_1;
    ImageView pic_2;
    LinearLayout out;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpicfrommap);

        Intent intent = getIntent();
        String spic = intent.getStringExtra("PIC");
        String spic_1 = intent.getStringExtra("PIC_1");
        String spic_2 = intent.getStringExtra("PIC_2");

        pic = (ImageView)findViewById(R.id.imageView3);
        pic_1 = (ImageView)findViewById(R.id.imageView4);
        pic_2 = (ImageView)findViewById(R.id.imageView5);

        out = (LinearLayout)findViewById(R.id.sangjo);

        new ImageDownloader(pic).execute(spic);
        new ImageDownloader(pic_1).execute(spic_1);
        new ImageDownloader(pic_2).execute(spic_2);



        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_viewpicfrommap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
