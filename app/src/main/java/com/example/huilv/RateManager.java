package com.example.huilv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RateManager {
    private static final  String TAG="db";
    DBHelper dbHelper;
    String TB_NAME;

    public RateManager(Context context){
        this.dbHelper=new DBHelper(context);
        this.TB_NAME=DBHelper.TB_NAME;
    }

    public void add(Rateltem item){
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("curname",item.getCurname());
        values.put("currate",item.getCurrate());

        long r=db.insert(TB_NAME,null,values);
        db.close();

        Log.i(TAG,"add:写入结果r="+r);

    }
    public void addAll(List<Rateltem> list, ContentValues values){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        for(Rateltem item:list){
            values.put("curname",item.getCurname());
            values.put("currate",item.getCurrate());
            db.insert(TB_NAME,null,values);
        }
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(TB_NAME,null,null);
        db.close();
    }
    public void delete(int id){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.delete(TB_NAME,"ID=?",new  String[]{String.valueOf(id)});
        db.close();
    }
    public void update(Rateltem item){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        
    }
    public List<Rateltem> listAll(){
        List<Rateltem> retList = new ArrayList<Rateltem>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TB_NAME,null,null,
                null,null,null,null);

        if (cursor != null){
            while(cursor.moveToNext()){
                Rateltem item = new Rateltem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurname(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setCurrate(cursor.getString(cursor.getColumnIndex("CURRATE")));
                retList.add(item);
            }
            cursor.close();
        }
        db.close();
        return retList;
    }

}
