package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.payroll.GlobalVariable;

public class DBPayment  extends SQLiteOpenHelper {
    public DBPayment( Context context) {
        super(context, "Payments.db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Payment (mobno TEXT ,Description TEXT,Date TEXT ,advance  INTEGER  ,pending  INTEGER,ownerMobno TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Payment");
        onCreate(DB);
    }
    public Boolean insertuserdata(String mobno,String Description,String Date,Integer advance,Integer pending)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("mobno", mobno);
        contentValues.put("Description",Description);
        contentValues.put("Date", Date);
        contentValues.put("advance", advance);
        contentValues.put("pending", pending);
        contentValues.put("ownerMobno", GlobalVariable.mob_no);


        long result=DB.insert("Payment", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Payment ", null);
        return cursor;

    }
    public Boolean count()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = DB.rawQuery("SELECT * FROM Payment ", null);
        cur.moveToNext();
        while (cur.moveToNext()) {
            return empty;
        }
        empty=false;
        return  empty;

    }

}
