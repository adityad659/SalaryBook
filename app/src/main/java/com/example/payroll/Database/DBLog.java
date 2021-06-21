package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBLog  extends SQLiteOpenHelper {
    public DBLog( Context context) {
        super(context, "Userdato.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userlog (phno TEXT primary key,log  TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userlog");
        onCreate(DB);

    }
    public Boolean insertuserdata(String phno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phno", phno);
        contentValues.put("log", "in");


        long result=DB.insert("Userlog", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userlog", null);
        return cursor;

    }

    public Boolean updateuserdata(String phno,String log) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("log", log);

        Cursor cursor = DB.rawQuery("Select * from Userlog where phno = ?", new String[]{phno});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userlog", contentValues, "phno=?", new String[]{phno});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
}
