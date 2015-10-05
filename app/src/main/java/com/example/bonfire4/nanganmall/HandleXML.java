package com.example.bonfire4.nanganmall;

/**
 * Created by bonfire4 on 2015. 3. 3..
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class HandleXML {



    private boolean inItem,inMapx,inMapy = false;
    String mapx,mapy = null;

    private URL urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    public HandleXML(URL url){
        this.urlString = url;
    }

    public String getX() {
        return mapx;
    }

    public String getY() {
        return mapy;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){
                switch(event){
                    case XmlPullParser.START_TAG:  //parser가 시작 태그를 만나면 실행

                        if(myParser.getName().equals("item")){
                            inItem = true;
                        }

                        if(myParser.getName().equals("x")){ //mapx 만나면 내용을 받을수 있게 하자
                            inMapx = true;
                        }
                        if(myParser.getName().equals("y")){ //mapy 만나면 내용을 받을수 있게 하자
                            inMapy = true;
                        }

                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때

                        if(inMapx){ //isMapx이 true일 때 태그의 내용을 저장.
                            mapx = myParser.getText();
                            inMapx = false;
                        }
                        if(inMapy){ //isMapy이 true일 때 태그의 내용을 저장.
                            mapy = myParser.getText();
                            inMapy = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(myParser.getName().equals("item")){

                            map nmap = new map();
                            nmap.setCenterfromSearch(Double.parseDouble(mapx),Double.parseDouble(mapy));
                            inItem = false;
                        }
                        break;
                }
                event = myParser.next();
            }

        } catch(Exception e){

        }

    }
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = urlString;
                    HttpURLConnection conn = (HttpURLConnection)
                            url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                            , false);
                    myparser.setInput(stream, "utf-8");
                    parseXMLAndStoreIt(myparser);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


    }

}