package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.payroll.GlobalVariable;

public class DBTotal   extends SQLiteOpenHelper {
    public DBTotal( Context context) {
        super(context, "Total.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Totals (mobno String primary key,balance Integer,ownerMobno TEXT,ownerName TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Totals");
        onCreate(DB);
    }
    public Boolean insertuserdata(String mobno,Integer balance)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mobno", mobno);
        contentValues.put("balance", balance);
        contentValues.put("ownerMobno", GlobalVariable.mob_no);
        contentValues.put("ownerName", GlobalVariable.Owner_nm);

        long result=DB.insert("Totals", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Totals ", null);
        return cursor;

    }
    public Boolean count()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = DB.rawQuery("SELECT * FROM Totals ", null);
        cur.moveToNext();
        while (cur.moveToNext()) {
            return empty;
        }
        empty=false;
        return  empty;

    }
    public Boolean updateuserdata(String mobno,Integer balance) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("ownerMobno", GlobalVariable.mob_no);
        contentValues.put("ownerName", GlobalVariable.Owner_nm);
        contentValues.put("balance", balance);


        Cursor cursor = DB.rawQuery("Select * from Totals where mobno = ? ", new String[]{mobno});
        if (cursor.getCount() > 0) {
            long result = DB.update("Totals", contentValues, "mobno=? ", new String[]{mobno});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

}
