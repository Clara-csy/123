package com.example.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HuilvjiemianActivity2 extends AppCompatActivity {
        private static final String TAG ="HuilvjiemianActivity2";
        EditText dollarEditor,euroEditor,wonEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huilvjiemian2);
        //接受数据
        Intent conf = getIntent();
        float dollar = conf.getFloatExtra( "dollar_rate_key",0.0f);
        float euro = conf.getFloatExtra( "euro_rate_key",0.0f);
        float won = conf.getFloatExtra( "won_rate_key",0.0f);

        Log.i(TAG, "onCreate: dollar=" + dollar);
        Log.i(TAG, "onCreate: euro=" + dollar);
        Log.i(TAG, "onCreate: won=" + dollar);

        dollarEditor = (EditText) findViewById(R.id.edit_dollar);
        euroEditor = (EditText) findViewById(R.id.edit_euro);
        wonEditor = (EditText) findViewById(R.id.edit_won);

        dollarEditor.setText(String.valueOf(dollar));
        euroEditor.setText(String.valueOf(euro));
        wonEditor.setText(String.valueOf(won));

    }
    public void save(View btn){
        Log.i(TAG,"save: ");
        float newDollar = Float.parseFloat(dollarEditor.getText().toString());
        float newEuroi = Float.parseFloat(euroEditor.getText().toString());
        float newWon = Float.parseFloat(wonEditor.getText().toString());
        Log.i(TAG,"save: 获取到新的值");
        Log.i(TAG,"onCreate:newDollar=" + newDollar);
        Log.i(TAG,"onCreate:newEuro=" + newEuroi);
        Log.i(TAG,"onCreate:newWon=" + newWon);

        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("key_dollar",newDollar);
        bdl.putFloat("key_euro",newDollar);
        bdl.putFloat("key_won",newDollar);
        intent.putExtras(bdl);
        setResult(2,intent);

        finish();
    }
}
