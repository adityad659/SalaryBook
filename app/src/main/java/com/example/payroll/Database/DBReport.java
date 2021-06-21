package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.payroll.GlobalVariable;

public class  DBReport extends SQLiteOpenHelper {
    public DBReport( Context context) {
        super(context, "report.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table reports (mobno String,date String ,calcsalary Integer,payment Integer,lastClosingBalance Integer,closingBalance Integer ,ownerMobno TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists reports");
        onCreate(DB);
    }
    public Boolean insertuserdata(String mobno,String date,Integer calcsalary,Integer payment,Integer lastClosingBalance,Integer closingBalance)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mobno", mobno);
        contentValues.put("date",date );


        contentValues.put("calcsalary", calcsalary);
        contentValues.put("payment", payment);
        contentValues.put("lastClosingBalance", lastClosingBalance);
        contentValues.put("closingBalance", closingBalance);
        contentValues.put("ownerMobno", GlobalVariable.mob_no);



        long result=DB.insert("reports", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from reports", null);
        return cursor;

    }
    public Boolean count()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = DB.rawQuery("SELECT * FROM reports ", null);
        cur.moveToNext();
        while (cur.moveToNext()) {
            return empty;
        }
        empty=false;
        return  empty;

    }
    public Boolean updateuserdata(String mobno,String date,Integer calcsalary,Integer payment,Integer lastClosingBalance,Integer closingBalance) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



         contentValues.put("calcsalary", calcsalary);
        contentValues.put("payment", payment);
        contentValues.put("lastClosingBalance", lastClosingBalance);
        contentValues.put("closingBalance", closingBalance);
        contentValues.put("ownerMobno", GlobalVariable.mob_no);


        Cursor cursor = DB.rawQuery("Select * from reports where mobno = ? and date=?", new String[]{mobno,date});
        if (cursor.getCount() > 0) {
            long result = DB.update("reports", contentValues, "mobno=? and date=?", new String[]{mobno,date});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

}
