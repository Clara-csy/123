package com.example.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView score;
    TextView score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score2);

        score = (TextView)findViewById(R.id.score);
        score2 = (TextView)findViewById(R.id.score2);
    }
    public void btnAdd1(View btn) {
        if(btn_getId()==R.id.btn_1)
        showScore(1);
    }

    private void showScore(int i){
    }
    private int btn_getId() {
        return 0;
    }

    public void btnAdd2(View view) {
        if(btn_getId()==R.id.btn_2)
            showScore(2);
    }

    public void btnAdd3(View view) {
        if(btn_getId()==R.id.btn_3)
        showScore(3);
    }

    public void btnReset(View view){
    }
}