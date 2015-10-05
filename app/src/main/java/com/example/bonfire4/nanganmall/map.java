package com.example.bonfire4.nanganmall;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class map extends NMapActivity
        implements OnMapStateChangeListener {





//변수선언

    //네이버 맵 구현용

    // API-KEY
    public static final String API_KEY = "f3123d03e752f3ecc8c527e19ce063a7";
    // 네이버 맵 객체
    NMapView mMapView = null;
    // 맵 컨트롤러
    NMapController mMapController = null;
    // 맵을 추가할 레이아웃
    LinearLayout MapContainer;
    //마커설치용 오버레이 매니저
    NMapOverlayManager mOverlayManager;
    //NMapCompassManager mMapCompassManager;
    NMapViewerResourceProvider mMapViewerResourceProvider;

    private static final String LOG_TAG = "map";
    private static final boolean DEBUG = false;


    //gps 정보 받기 (네이버api 썩어서 다른 것으로 대체)
    private GpsInfo gps;

    //php 연결 및 DB값 받아오기
    private String jsonResult;

    private String url = "http://redphant.godohosting.com/test.php";

    private String[] mtitle;
    private String[] maddress;
    private String[] mpicture;
    private String[] mpicture_1;
    private String[] mpicture_2;
    private String[] mwhi;
    private String[] mwhan;
    private String[] mtype;
    private double[] mlng;
    private double[] mlat;


    //맵 아래 화면 구성 뷰
    TextView vtitle;
    TextView vwhi;
    TextView vwhan;
    TextView vaddress;
    TextView vtype;
    Button picbutton;


    //검색창
    Button searchBT;
    EditText searchET;

    boolean inItem,inMapx,inMapy = false;
    String mapx,mapy = null;


    XmlPullParserFactory xmlFactoryObject;
    URL nurl;










    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);





//초기화

        // 네이버 지도를 넣기 위한 LinearLayout 컴포넌트
        MapContainer = (LinearLayout) findViewById(R.id.MapContainer);

        // 네이버 지도 객체 생성
        mMapView = new NMapView(this);

        // 지도 객체로부터 컨트롤러 추출
        mMapController = mMapView.getMapController();

        // 네이버 지도 객체에 APIKEY 지정
        mMapView.setApiKey(API_KEY);

        // 생성된 네이버 지도 객체를 LinearLayout에 추가시킨다.
        MapContainer.addView(mMapView);

        // 지도를 터치할 수 있도록 옵션 활성화
        mMapView.setClickable(true);

        // 확대/축소를 위한 줌 컨트롤러 표시 옵션 활성화
        mMapView.setBuiltInZoomControls(true, null);

        // 지도에 대한 상태 변경 이벤트 연결
        mMapView.setOnMapStateChangeListener(this);

        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

        vtitle = (TextView)findViewById(R.id.vtitle);
        vwhi= (TextView)findViewById(R.id.vwhi);
        vwhan = (TextView)findViewById(R.id.vwham);
        vaddress = (TextView)findViewById(R.id.vaddress);
        vtype = (TextView)findViewById(R.id.vtype);
        picbutton = (Button)findViewById(R.id.button3);

        //검색창
        searchBT = (Button)findViewById(R.id.button);
        searchET = (EditText)findViewById(R.id.editText);









//버튼 리스너 처리

        ImageButton btnCallgps = (ImageButton) findViewById(R.id.imageButton);
        btnCallgps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick", "Callgps");
                gps = new GpsInfo(map.this);
                // GPS 사용유무 가져오기
                if (gps.isGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();


                    Toast.makeText(
                            getApplicationContext(),
                            "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,
                            Toast.LENGTH_LONG).show();
                    mMapController.setMapCenter(
                            new NGeoPoint(longitude, latitude), 7);

                } else {
                    // GPS 를 사용할수 없으므로
                    gps.showSettingsAlert();
                }
            }
        });

        ImageButton btnCallhome = (ImageButton) findViewById(R.id.imageButton7);
        btnCallhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick", "Callhome");
                onBackPressed();
            }
        });

        searchBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                last();
            }
        });


        Button sort_total = (Button) findViewById(R.id.button4);
        sort_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nanganPOIdataOverlay();

            }
        });


        Button sort_special = (Button) findViewById(R.id.button5);
        sort_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortPOIDATA("하나로레일");

            }
        });

        Button sort_hanaro = (Button) findViewById(R.id.button6);
        sort_hanaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortPOIDATA("강화유리난간");

            }
        });

        Button sort_1 = (Button) findViewById(R.id.button7);
        sort_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortPOIDATA("간살형난간");

            }
        });

        Button sort_2 = (Button) findViewById(R.id.button8);
        sort_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                vtitle.setText("");
                vwhi.setText("");
                vtype.setText("");
                vaddress.setText("");
                vwhan.setText("");
                picbutton.setVisibility(View.INVISIBLE);

                //sortPOIDATA("세개로");
                Toast.makeText(
                        getApplicationContext(),
                        "데이터 준비중입니다.",
                        Toast.LENGTH_LONG).show();

            }
        });



        //DB값을 받아와 변수로 세팅
        accessWebService();


    }









    //마커찍는 함수
    private void nanganPOIdataOverlay() {

        // Markers for POI item
        int markerId = NMapPOIflagType.PIN;
        int n = mtitle.length;
        //double[] mlng = {127.0630205,127.2012478,127.0752085,127.1128025};
        //double[] mlat = {37.5091300,36.5702302,37.2535544,37.3946784};
        //String[] mtitle = {"삼성역","세종시","비와이통상","판교역"};
        int i = 0;


        // set POI data
        NMapPOIdata poiData = new NMapPOIdata(n, mMapViewerResourceProvider);
        poiData.beginPOIdata(n);

        for(i=0; i < n; i++){
            NMapPOIitem item =poiData.addPOIitem(mlng[i],mlat[i],mtitle[i], markerId, i);
            item.setRightAccessory(true, NMapPOIflagType.CLICKABLE_ARROW);
        }

        poiData.endPOIdata();

        // create POI data overlay
        final NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        // set event listener to the overlay
        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);



        vtitle.setText("현재 모든 타입의 현장이 표시되도록 설정되었습니다.");
        vtype.setText("전국에 "+n+"개의 현장이 등록되어 있습니다.");
        vaddress.setVisibility(View.INVISIBLE);
        vwhan.setVisibility(View.INVISIBLE);
        picbutton.setVisibility(View.INVISIBLE);

        vwhi.setText("모든 현장이 나오도록 지도를 조정하려면 여기를 클릭하세요");

        vwhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                poiDataOverlay.showAllPOIdata(0);


            }
        });





    }

    //말풍선 클릭시 액션정의
    private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {

        @Override
        public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, final NMapPOIitem item) {
            if (DEBUG) {
                Log.i(LOG_TAG, "onCalloutClick: title=" + item.getTitle());
            }

            // [[TEMP]] handle a click event of the callout
            //Toast.makeText(map.this, "No.of array is.." + item.getId(), Toast.LENGTH_LONG).show();


            picbutton.setVisibility(View.VISIBLE);
            vaddress.setVisibility(View.VISIBLE);
            vwhan.setVisibility(View.VISIBLE);
            vwhi.setVisibility(View.VISIBLE);
            picbutton.setVisibility(View.VISIBLE);

            vtitle.setText("현장명 : " + mtitle[item.getId()]);
            vwhi.setText("시공자 : " + mwhi[item.getId()]);
            vwhan.setText("시공일 : " + mwhan[item.getId()]);
            vaddress.setText("현장주소 : " + maddress[item.getId()]);
            vtype.setText("설치타입 : " + mtype[item.getId()]);



            picbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("OnClick", "Callpic");

                    Intent intent = new Intent(map.this, Viewpicfrommap.class);
                    intent.putExtra("PIC","http://redphant.godohosting.com/wp-content/uploads/nanganmall/formap/"+mpicture[item.getId()]);
                    intent.putExtra("PIC_1","http://redphant.godohosting.com/wp-content/uploads/nanganmall/formap/"+mpicture_1[item.getId()]);
                    intent.putExtra("PIC_2","http://redphant.godohosting.com/wp-content/uploads/nanganmall/formap/"+mpicture_2[item.getId()]);

                    startActivity(intent);



                }
            });










        }

        @Override
        public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
            if (DEBUG) {
                if (item != null) {
                    Log.i(LOG_TAG, "onFocusChanged: " + item.toString());
                } else {
                    Log.i(LOG_TAG, "onFocusChanged: ");
                }
            }
        }
    };



    /**
     * 지도가 초기화된 후 호출된다.
     * 정상적으로 초기화되면 errorInfo 객체는 null이 전달되며,
     * 초기화 실패 시 errorInfo객체에 에러 원인이 전달된다
     */
    @Override
    public void onMapInitHandler(NMapView mapview, NMapError errorInfo) {
        if (errorInfo == null) { // success



                gps = new GpsInfo(map.this);
                // GPS 사용유무 가져오기
                if (gps.isGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();


                    Toast.makeText(
                            getApplicationContext(),
                            "현재위치로 설정되었습니다.",
                            Toast.LENGTH_LONG).show();
                    mMapController.setMapCenter(
                            new NGeoPoint(longitude, latitude), 7);

                } else {

                    // GPS 를 사용할수 없으므로
                    mMapController.setMapCenter(
                            new NGeoPoint(127.0752085, 37.2535544), 7);
               }










        } else { // fail
            android.util.Log.e("NMAP", "onMapInitHandler: error="
                    + errorInfo.toString());
        }
    }

    /**
     * 지도 레벨 변경 시 호출되며 변경된 지도 레벨이 파라미터로 전달된다.
     */
    @Override
    public void onZoomLevelChange(NMapView mapview, int level) {}

    /**
     * 지도 중심 변경 시 호출되며 변경된 중심 좌표가 파라미터로 전달된다.
     */
    @Override
    public void onMapCenterChange(NMapView mapview, NGeoPoint center) {}

    /**
     * 지도 애니메이션 상태 변경 시 호출된다.
     * animType : ANIMATION_TYPE_PAN or ANIMATION_TYPE_ZOOM
     * animState : ANIMATION_STATE_STARTED or ANIMATION_STATE_FINISHED
     */
    @Override
    public void onAnimationStateChange(
            NMapView arg0, int animType, int animState) {}

    @Override
    public void onMapCenterChangeFine(NMapView arg0) {}





    // Async Task to access the web

    private class JsonReadTask extends AsyncTask<String, Void, String> {

        @Override

        protected String doInBackground(String... params) {

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(params[0]);

            try {

                HttpResponse response = httpclient.execute(httppost);

                jsonResult = inputStreamToString(

                        response.getEntity().getContent()).toString();

            }


            catch (ClientProtocolException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return null;

        }


        private StringBuilder inputStreamToString(InputStream is) {

            String rLine = "";

            StringBuilder answer = new StringBuilder();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));


            try {

                while ((rLine = rd.readLine()) != null) {

                    answer.append(rLine);

                }

            }


            catch (IOException e) {

                // e.printStackTrace();

                Toast.makeText(getApplicationContext(),

                        "Error..." + e.toString(), Toast.LENGTH_LONG).show();

            }

            return answer;

        }


        @Override

        protected void onPostExecute(String result) {

            ListDrwaer();

        }

    }// end async task

    //php 연결을 위해 최초 호출되는 함수. asynctast 를 호출한다
    public void accessWebService() {

        JsonReadTask task = new JsonReadTask();

        // passes values for the urls string array

        task.execute(new String[] { url });

    }



    // php를 통해서 넘어온 DB 값인 jsonobject를 통해 필요한 배열을 생산한다.
    public void ListDrwaer() {

       // List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();


        try {

            JSONObject jsonFirstObj= new JSONObject(jsonResult);

            JSONArray jsonArray1 = jsonFirstObj.optJSONArray("plz");



            mtitle= new String[jsonArray1.length()];
            maddress= new String[jsonArray1.length()];
            mpicture= new String[jsonArray1.length()];
            mpicture_1= new String[jsonArray1.length()];
            mpicture_2= new String[jsonArray1.length()];
            mwhi= new String[jsonArray1.length()];
            mwhan= new String[jsonArray1.length()];
            mtype= new String[jsonArray1.length()];
            mlng= new double[jsonArray1.length()];
            mlat= new double[jsonArray1.length()];

            for (int i=0; i<jsonArray1.length();i++){

                JSONObject jsonObject2 = jsonArray1.optJSONObject(i);

                mtitle[i] = jsonObject2.getString("title");
                maddress[i] = jsonObject2.getString("address");
                mpicture[i] = jsonObject2.getString("picture");
                mpicture_1[i] = jsonObject2.getString("picture_1");
                mpicture_2[i] = jsonObject2.getString("picture_2");
                mwhi[i] = jsonObject2.getString("whi");
                mwhan[i] = jsonObject2.getString("whan");
                mtype[i] = jsonObject2.getString("type");
                mlng[i] = Double.parseDouble(jsonObject2.getString("lng"));
                mlat[i] = Double.parseDouble(jsonObject2.getString("lat"));


            }

            //마커찍는 함수 호출
            nanganPOIdataOverlay();




        } catch (JSONException e) {

            Toast.makeText(getApplicationContext(), "Error" + e.toString(),

                    Toast.LENGTH_SHORT).show();

        }




    }





    public void last() {

        String query = searchET.getText().toString();
        double nx = 0;
        double ny = 0;


        try {



            nurl = new URL("http://openapi.map.naver.com/api/geocode?key=" + API_KEY + "&encoding=utf-8&coord=latlng&query=" + URLEncoder.encode(query, "utf-8"));

            fetchXML();











        } catch (IOException e) {

            e.printStackTrace();

        }}










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

                                setCenterfromSearch(Double.parseDouble(mapx),Double.parseDouble(mapy));
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
                        URL url = nurl;
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






    public void setCenterfromSearch(double a,double b){

        mMapController.setMapCenter(
                new NGeoPoint(a, b), 11);

    }


    private void sortPOIDATA(String stype) {

        mOverlayManager.clearOverlays();

        // Markers for POI item
        int markerId = NMapPOIflagType.PIN;

        int n = mtitle.length;


        int i = 0;


        // set POI data
        NMapPOIdata poiData = new NMapPOIdata(n, mMapViewerResourceProvider);
        poiData.beginPOIdata(n);

        for(i=0; i < n; i++){

            if (mtype[i].equals(stype)) {

                NMapPOIitem item =poiData.addPOIitem(mlng[i],mlat[i],mtitle[i], markerId, i);
                item.setRightAccessory(true, NMapPOIflagType.CLICKABLE_ARROW);

            }

        }

        poiData.endPOIdata();

        // create POI data overlay
        final NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

        //poiDataOverlay.showAllPOIdata(11);


        vtitle.setText("현재 "+ stype +" 타입의 현장만 나오도록 설정되었습니다.");
        //vtitle.setText("현재 전국에 "+poiDataOverlay.size()+"개의 "+stype+" 현장이 등록되어 있습니다.");
        vtype.setText("전국에 " +poiDataOverlay.size()+ "개의 "+ stype +" 타입의 현장이 등록되어 있습니다.");
        vaddress.setVisibility(View.INVISIBLE);
        vwhan.setVisibility(View.INVISIBLE);
        picbutton.setVisibility(View.INVISIBLE);


        vwhi.setVisibility(View.VISIBLE);
        vwhi.setText("선택한 타입 전체를 볼 수 있도록 지도를 조정하려면 여기를 클릭하세요.");

        vwhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                poiDataOverlay.showAllPOIdata(0);


            }
        });



        // set event listener to the overlay
        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);





    }









}




