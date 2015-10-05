package com.example.bonfire4.nanganmall;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class Ccatal extends Activity {

    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private ProgressDialog mProgressDialog;
    private Context context;



    String File_Name1 = "nosangjo_nangan_1.pdf";
    String File_Name2 = "nosangjo_nangan_2.pdf";
    String File_Name3 = "nosangjo_nangan_3.pdf";
    String File_Name4 = "nosangjo_nangan_4.pdf";


    String File_extend = "pdf";

    String fileURL = "http://redphant.godohosting.com/downloads"; // URL


    String file_url1 = fileURL+"/"+File_Name1;
    String file_url2 = fileURL+"/"+File_Name2;
    String file_url3 = fileURL+"/"+File_Name3;
    String file_url4 = fileURL+"/"+File_Name4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccatal);
        context = this;


        ImageButton btnCallhome = (ImageButton)findViewById(R.id.imageButton12);
        btnCallhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OnClick", "Callhome");
                onBackPressed();
            }
        });



        ImageButton btnCallCatal1 = (ImageButton)findViewById(R.id.imageButton_hanaro);
        btnCallCatal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                // 다운로드 폴더에 동일한 파일명이 존재하는지 확인해서
                // 없으면 다운받고 있으면 해당 파일 실행시킴.
                if (new File("/data/data/" + getPackageName() + "/files/" + File_Name1).exists() == false) {

                    new AlertDialog.Builder(Ccatal.this)
                            .setTitle("해당 카탈로그가 없습니다. 다운로드를 시작합니다")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new DownloadFileAsync().execute(file_url1, File_Name1, "1");
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();


                } else {
                    showDownloadFile(File_Name1);
                }
            }
        });

        ImageButton btnCallCatal2 = (ImageButton)findViewById(R.id.imageButton_yuri);
        btnCallCatal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                // 다운로드 폴더에 동일한 파일명이 존재하는지 확인해서
                // 없으면 다운받고 있으면 해당 파일 실행시킴.
                if (new File("/data/data/" + getPackageName() + "/files/" + File_Name2).exists() == false) {

                    new AlertDialog.Builder(Ccatal.this)
                            .setTitle("해당 카탈로그가 없습니다. 다운로드를 시작합니다")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new DownloadFileAsync().execute(file_url2, File_Name2, "1");
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();


                } else {
                    showDownloadFile(File_Name2);
                }
            }
        });


        ImageButton btnCallCatal3 = (ImageButton)findViewById(R.id.imageButton_gansal);
        btnCallCatal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                // 다운로드 폴더에 동일한 파일명이 존재하는지 확인해서
                // 없으면 다운받고 있으면 해당 파일 실행시킴.
                if (new File("/data/data/" + getPackageName() + "/files/" + File_Name3).exists() == false) {

                    new AlertDialog.Builder(Ccatal.this)
                            .setTitle("해당 카탈로그가 없습니다. 다운로드를 시작합니다")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new DownloadFileAsync().execute(file_url3, File_Name3, "1");
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();


                } else {
                    showDownloadFile(File_Name3);
                }
            }
        });

        ImageButton btnCallCatal4 = (ImageButton)findViewById(R.id.imageButton_etc);
        btnCallCatal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                // 다운로드 폴더에 동일한 파일명이 존재하는지 확인해서
                // 없으면 다운받고 있으면 해당 파일 실행시킴.
                if (new File("/data/data/" + getPackageName() + "/files/" + File_Name4).exists() == false) {

                    new AlertDialog.Builder(Ccatal.this)
                            .setTitle("해당 카탈로그가 없습니다. 다운로드를 시작합니다")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new DownloadFileAsync().execute(file_url4, File_Name4, "1");
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();


                } else {
                    showDownloadFile(File_Name4);
                }
            }
        });













        ImageButton btnCallCataldel = (ImageButton)findViewById(R.id.imageButton_reset);
        btnCallCataldel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                catalDel();

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ccatal, menu);
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





    private void showDownloadFile(String fn) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        File file = new File("/data/data/"+getPackageName() + "/files/" + fn);

        // 파일 확장자 별로 mime type 지정해 준다.
        if (File_extend.equals("mp3")) {
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
        } else if (File_extend.equals("mp4")) {
            intent.setDataAndType(Uri.fromFile(file), "vidio/*");
        } else if (File_extend.equals("jpg") || File_extend.equals("jpeg")
                || File_extend.equals("JPG") || File_extend.equals("gif")
                || File_extend.equals("png") || File_extend.equals("bmp")) {
            intent.setDataAndType(Uri.fromFile(file), "image/*");
        } else if (File_extend.equals("txt")) {
            intent.setDataAndType(Uri.fromFile(file), "text/*");
        } else if (File_extend.equals("doc") || File_extend.equals("docx")) {
            intent.setDataAndType(Uri.fromFile(file), "application/msword");
        } else if (File_extend.equals("xls") || File_extend.equals("xlsx")) {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.ms-excel");
        } else if (File_extend.equals("ppt") || File_extend.equals("pptx")) {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.ms-powerpoint");
        } else if (File_extend.equals("pdf")) {
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        }
        startActivity(intent);
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("다운로드중...");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                return mProgressDialog;

            default:
                return null;
        }
    }

    class DownloadFileAsync extends AsyncTask<String,String,String> {



        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            // TODO Auto-generated method stub
            int count;
            long total;
            int lengthOfFile;
            String fileName;

            try {
                URL url = new URL(aurl[0]);
                URLConnection conn = url.openConnection();
                conn.connect();

                fileName = new File(aurl[0]).getName();

                lengthOfFile = conn.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = openFileOutput(fileName, Context.MODE_WORLD_READABLE);

                byte data[] = new byte[1024];
                total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int)((total*100)/lengthOfFile));
                    output.write(data,0,count);
                }

                output.flush();
                output.close();
                input.close();
                showDownloadFile(aurl[1]);
            } catch (Exception e) {
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused) {
            // TODO Auto-generated method stub
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
            Toast.makeText(context, "다운로드 완료되었습니다.", Toast.LENGTH_LONG).show();


        }

    }



    public void catalDel() {

        deleteFile(File_Name1);
        deleteFile(File_Name2);
        deleteFile(File_Name3);
        deleteFile(File_Name4);

        Toast.makeText(context, "삭제완료. 새로운 카탈로그를 받을 수 있습니다.", Toast.LENGTH_LONG).show();


    }
}




