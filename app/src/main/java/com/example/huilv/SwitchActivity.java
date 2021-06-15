package com.example.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.huilv.ui.main.SwitchFragment;

public class SwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SwitchFragment.newInstance())
                    .commitNow();
        }
    }
}