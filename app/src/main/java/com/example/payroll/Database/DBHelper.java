package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
DB.execSQL("create Table Userdetails (phno TEXT primary key,ownerName TEXT,businessName TEXT,ispassword TEXT ,password TEXT ,isfingerprint TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
DB.execSQL("drop Table if exists Userdetails");
onCreate(DB);


    }
    public Boolean insertuserdata(String phno,String ownerName,String businessName)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phno", phno);
        contentValues.put("ownerName", ownerName);
        contentValues.put("businessName",businessName);
        contentValues.put("ispassword", "false");
        contentValues.put("password", "0");
        contentValues.put("isfingerprint", "false");


        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;

    }
    public Boolean updateuserdata(String phno,String ownerName, String businessName ,String ispassword, String password,String isfingerprint) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ownerName", ownerName);
        contentValues.put("businessName",businessName);

        contentValues.put("ispassword", ispassword);
        contentValues.put("password", password);
        contentValues.put("isfingerprint", isfingerprint);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where phno = ?", new String[]{phno});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "phno=?", new String[]{phno});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
    public Cursor getrecord (String phno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where phno = ?", new String[]{phno});
        return cursor;

    }
}
