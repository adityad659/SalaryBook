package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.payroll.GlobalVariable;

public class DBDate extends SQLiteOpenHelper {
    public DBDate( Context context) {
        super(context, "date.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table dates (date String primary key,ownerMobno TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists dates");
        onCreate(DB);
    }
    public Boolean insertuserdata(String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("ownerMobno", GlobalVariable.mob_no);

        long result=DB.insert("dates", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from dates", null);
        return cursor;

    }
    public Boolean count()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = DB.rawQuery("SELECT * FROM dates ", null);
        cur.moveToNext();
        while (cur.moveToNext()) {
            return empty;
        }
        empty=false;
        return  empty;

    }
}
