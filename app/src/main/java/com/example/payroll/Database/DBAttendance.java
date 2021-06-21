package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.payroll.GlobalVariable;

public class DBAttendance  extends SQLiteOpenHelper {
    public DBAttendance( Context context) {
        super(context, "EmpAttendance.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Attendancedetails (empName TEXT,mobno TEXT ,date TEXT,attendance TEXT ,ownerMobno TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Attendancedetails");
        onCreate(DB);
    }
    public Boolean insertuserdata(String name,String mobno,String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("empName",name );
        contentValues.put("mobno", mobno);
        contentValues.put("date",date);
        contentValues.put("attendance", "present");
        contentValues.put("ownerMobno", GlobalVariable.mob_no);



        long result=DB.insert("Attendancedetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Attendancedetails  ", null);
        return cursor;

    }
    public Boolean count()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean empty = true;
        Cursor cur = DB.rawQuery("SELECT * FROM Attendancedetails ", null);
        cur.moveToNext();
        while (cur.moveToNext()) {
            return empty;
        }
        empty=false;
        return  empty;

    }
    public Boolean updateuserdata(String name,String mobno, String date ,String attendance) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("empName", name);
        contentValues.put("attendance", attendance);
        contentValues.put("ownerMobno", GlobalVariable.mob_no);


        Cursor cursor = DB.rawQuery("Select * from Attendancedetails where mobno = ? and date=?", new String[]{mobno,date});
        if (cursor.getCount() > 0) {
            long result = DB.update("Attendancedetails", contentValues, "mobno=? and date=?", new String[]{mobno,date});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
}
