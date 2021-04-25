package com.example.huilv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements  Runnable {
    private static final String TAG = "MainActivity";
    EditText input;

    TextView result;
    float dollarRate = 0.15f;
    float euroRate = 0.12f;
    float wonRate = 172.0f;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        result = (TextView) findViewById(R.id.resultOut);
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);

        dollarRate = sharedPreferences.getFloat("dollar_rate", 0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate", 0.0f);
        wonRate = sharedPreferences.getFloat("won_rate", 0.0f);

        Thread t = new Thread((Runnable) this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 7) {
                    String str = (String) msg.obj;
                    Log.i(TAG, "handlerMessage:get str=" + str);
                    result.setText(str);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View btn) {
        //获取用户输入内容
        String str = input.getText().toString();
        float r = 0;
        if (str.length() > 0) {
            r = Float.parseFloat(str);
        }
    }

    public void openOne(View btn) {
        //打开一个界面
        Log.i("open", "openOne: ");
        Intent hello = new Intent(this, HuilvjiemianActivity2.class);
        startActivity(hello);
    }

    public void click(View btn) {
        Log.d(TAG, "click: ");

        float r = 0.0f;
        switch (btn.getId()) {
            case R.id.btn_dollar:
                r = dollarRate;
                break;
            case R.id.btn_euro:
                r = euroRate;
                break;
            case R.id.btn_won:
                r = wonRate;
        }
        //获取用户输入
        String str = input.getText().toString();
        Log.i(TAG, "click: str=" + str);
        if (str == null || str.length() == 0) {
            //提示
            Toast.makeText(this, "Please input RMB", Toast.LENGTH_SHORT).show();
        } else {
            //进行计算
            //......
            result.setText("1234.4444");
        }
    }

    public void openHuilvjiemianActicity2(View btn) {
        Log.i(TAG, "openConfig:...");
        Intent config = new Intent(this, HuilvjiemianActivity2.class);
        //加入传递的参数
        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("dollar_rate_key", euroRate);
        config.putExtra("dollar_rate_key", wonRate);

        Log.i(TAG, "openOne: dollarRate=" + dollarRate);
        Log.i(TAG, "openOne: euroRate=" + euroRate);
        Log.i(TAG, "openOne: wonRate=" + wonRate);
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollar_rate", dollarRate);
        editor.putFloat("euro_rate", euroRate);
        editor.putFloat("won_rate", wonRate);
        editor.apply();

        startActivityForResult(config, 1);
    }

    public void run() {
        Log.i(TAG, "run:............");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = null;
        try {
            url = new URL("www.usd-com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);
            Log.i(TAG, "run: html=" + html);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Message msg = handler.obtainMessage(7);
        msg.obj = "from message";
        handler.sendMessage(msg);
    }

    private String inputStream2String(InputStream inputStream)
        throws IOException {
            final int bufferSize = 1024;
            final char[]buffer = new char[bufferSize];
            final StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(inputStream,"gb2312");
            while (true){
                int rsz = in.read(buffer,0,buffer.length);
                if(rsz<0)
                    break;
                out.append(buffer,0,rsz);
        }
         return out.toString();
    }
}
