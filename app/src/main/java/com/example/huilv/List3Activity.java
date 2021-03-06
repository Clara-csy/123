package com.example.huilv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class List3Activity extends ListActivity implements Runnable, AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    Handler handler;
    private ArrayList<HashMap<String, String>> listItems;
    private SimpleAdapter listItemAdapter;

    private static final String TAG="List3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();

        this.setListAdapter(listItemAdapter);

        Thread t=new Thread(this);
        t.start();


        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what ==7) {
                    List<HashMap<String,String>> list2 = (List<HashMap<String,String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(List3Activity.this, list2,
                            R.layout.list_item,
                            new String[]{"ItemTitle", "ItemDetail"},
                            new int[]{R.id.itemTitle, R.id.itemDetail}
                    );
                    setListAdapter(listItemAdapter);

                }
                super.handleMessage(msg);
            }
        };
        //MyAdapter adapter = new MyAdapter(this, R.layout.list_item, listItems);
        //setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }

    private void initListView() {
        //data
       // listItems = new ArrayList<HashMap<String, String>>();
        //for (int i = 0; i < 50; i++) {
           // HashMap<String, String> map = new HashMap<String, String>();
            //map.put("ItemTitle", "Rate: " + i);
            //map.put("ItemDetail", "detail" + i);
            //listItems.add(map);
        }
        //adapter
       //listItemAdapter = new SimpleAdapter(this,
               // listItems,
               // R.layout.list_item,
               //new String[]{"ItemTitle", "ItemDetail"},
                //new int[]{R.id.itemTitle, R.id.itemDetail});


    @Override
    public void run() {
        List<HashMap<String,String>>retList=new ArrayList<HashMap<String, String>>();
        Document document = null;
        try {
            Thread.sleep(5000);
            document = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/ ").get();
            Log.i(TAG,"run:" + document.title());
            Elements table = document.getElementsByTag("table");

            Element table2 = table.get(1);

            Elements tds = table2.getElementsByTag("td");
            for(int i = 0;i<tds.size();i+=8){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);

                String str1 = td1.text();
                String val = td2.text();

                Log.i(TAG,"run:" + str1 + "==>" + val);

                HashMap<String,String>map=new HashMap<String, String>();
                map.put("ItemTitle", str1);
                map.put("ItemDetail", val);
                retList.add(map);
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Message msg=handler.obtainMessage(7);
        msg.obj=retList;
        handler.sendMessage(msg);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       Log.i(TAG,"onItemClick: parent="+parent);
       Log.i(TAG,"onItemClick: view="+view);
       Log.i(TAG,"onItemClick: position="+position);
  //       listItems.remove(position);
 //        listItemAdapter.notifyDataSetChanged();
        //???listview?????????????????????
        HashMap<String,String>map= (HashMap<String, String>) getListView().getItemAtPosition(position);
        getListView().getItemAtPosition(position);
        Log.i(TAG,"onItemClick: id="+id);
        String titleStr=map.get("ItemTitle");
        String detailStr=map.get("ItemDetail");
        Log.i(TAG,"onItemClick: titleStr="+ titleStr);
        Log.i(TAG,"onItemClick: detailStr="+detailStr);

        //???View?????????????????????
        TextView title=(TextView)view.findViewById(R.id.itemTitle);
        TextView detail=(TextView)view.findViewById(R.id.itemDetail);
        String title2=String.valueOf(title.getText());
        String detail2=String.valueOf(detail.getText());
        Log.i(TAG,"onItemClick: title2="+ title2);
        Log.i(TAG,"onItemClick: detail2="+ detail2);
        //????????????
  //      Intent rateCalc=new Intent(this,RateCalcActivity.class);
  //      rateCalc.putExtra("title",titleStr);
  //      rateCalc.putExtra("rate",Float.parseFloat(detailStr));
  //      startActivity(rateCalc);
        Intent caleIntent=new Intent(this,RateCalcActivity.class);
        caleIntent.putExtra("title",titleStr);
        caleIntent.putExtra("rate",Float.parseFloat(detailStr));
        startActivity(caleIntent);


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG,"onItemLongClick:???????????????position="+position);
        //????????????
        //???????????????
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("??????").setMessage("?????????????????????????????????").setPositiveButton("???", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG,"onClick:????????????????????????");
                        listItems.remove(position);
                        listItemAdapter.notifyDataSetChanged();
                    }
                }).setPositiveButton("???",null);
                builder.create().show();
                Log.i(TAG,"onItemLongClick:size="+listItems.size());

                return true;
    }
}