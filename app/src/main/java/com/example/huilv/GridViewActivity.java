package com.example.huilv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        GridView gridView=findViewById(R.id.mygridview);

        List<String> list1 = new ArrayList<String>();
        for(int i=1; i<100;i++){
            list1.add("item" + i);//若要显示没有数据，将这个隐藏
        }

        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list1);
        gridView.setAdapter(adapter);
        gridView.setEmptyView(findViewById(R.id.nodata));//表示没有数据

    }
}