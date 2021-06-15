package com.example.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ToupiaoActivity extends AppCompatActivity {
    private static final String TAG ="ToupiaoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_toupiao );
    }

    public void onClick(View view) {
    }
    private class VoteTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            for(String p:params){
                Log.i( TAG,"doInBackground: "+p );
            }
            String ret=doVote(params[0]);
            return ret;
        }
        @Override
        protected void onPostExecute(String s){
            Toast.makeText( ToupiaoActivity.this,s,Toast.LENGTH_SHORT ).show();
        }

        private String doVote(String voteStr) {
            String reStr="";
            Log.i("vote","doVote() voteStr:"+voteStr);
            try {
                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append("r=").append( URLEncoder.encode(voteStr,"utf-8"));

                byte[]date=stringBuffer.toString().getBytes();
                String urlPath ="http://192.168.1.102:8000/vote/GetVote";
                URL url=new URL(urlPath);

                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setUseCaches(false);

                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                httpURLConnection.setRequestProperty("Content-Length",String.valueOf(date.length));
                OutputStream outputStream=httpURLConnection.getOutputStream();
                outputStream.write(date);

                int response=httpURLConnection.getResponseCode();
                if(response==HttpURLConnection.HTTP_OK){
                    InputStream inputStream=httpURLConnection.getInputStream();
                    reStr=inputStreamToString(inputStream);
                    Log.i("vote","reStr:"+reStr);
                }


            } catch (UnsupportedEncodingException | MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return reStr;
        }

        private String inputStreamToString(InputStream inputStream) {
            String resulData=null;
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            byte[]data=new byte[1024];
            int len=0;
            try{
                while((len = inputStream.read(data))!=-1){
                    byteArrayOutputStream.write( data,0,len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            resulData=new String(byteArrayOutputStream.toByteArray());
            return resulData;
        }
    }
}