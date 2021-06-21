package com.example.payroll.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.payroll.data.empdata;
import com.example.payroll.GlobalVariable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBEmp extends SQLiteOpenHelper {
    public DBEmp( Context context) {
        super(context, "Empdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Empdetails (empName TEXT,mobno TEXT primary key,salaryType TEXT,salary INTEGER ,advance  INTEGER  ,pending  INTEGER,date String,ownerMobno TEXT,ownerName TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Empdetails");
        onCreate(DB);
    }
    public Boolean insertuserdata(empdata data)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("empName", data.getEmpName());
        contentValues.put("mobno",  data.getMobno());
        contentValues.put("ownerMobno", GlobalVariable.mob_no);
        contentValues.put("ownerName", GlobalVariable.Owner_nm);
        contentValues.put("salaryType",  data.getSalaryType());
        contentValues.put("salary",  data.getSalary());
        contentValues.put("advance",  data.getAdvance());
        contentValues.put("pending", data.getPending());
        Calendar rightNow = Calendar.getInstance();
        String  ym=  new SimpleDateFormat("dd MMMM yyyy").format(rightNow.getTime());

        contentValues.put("date",ym);


        long result=DB.insert("Empdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
      Cursor cursor = DB.rawQuery("Select * from Empdetails  ", null);
      return cursor;


    }
public Boolean count()
{
    SQLiteDatabase DB = this.getWritableDatabase();
    boolean empty = true;
    Cursor cur = DB.rawQuery("SELECT * FROM Empdetails ", null);
    cur.moveToNext();
    while (cur.moveToNext()) {
       return empty;
    }
   empty=false;
    return  empty;

}
public Boolean updateuserdata(String name,String mobno, String salaryType ,Integer salary, Integer advance,Integer pending) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("empName", name);
        contentValues.put("ownerMobno", GlobalVariable.mob_no);
        contentValues.put("ownerName", GlobalVariable.Owner_nm);
        contentValues.put("salaryType", salaryType);
        contentValues.put("salary", salary);
        contentValues.put("advance", advance);
        contentValues.put("pending",pending);
        Calendar rightNow = Calendar.getInstance();
        String  ym=  new SimpleDateFormat("dd MMMM yyyy").format(rightNow.getTime());

        contentValues.put("date",ym);

        Cursor cursor = DB.rawQuery("Select * from Empdetails where mobno = ?", new String[]{mobno});
        if (cursor.getCount() > 0) {
            long result = DB.update("Empdetails", contentValues, "mobno=?", new String[]{mobno});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
    public Cursor getrecord(String mobno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Empdetails where mobno = ?", new String[]{mobno});
        return cursor;


    }
}
