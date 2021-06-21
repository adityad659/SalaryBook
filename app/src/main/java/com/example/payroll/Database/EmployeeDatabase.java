package com.example.payroll.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.payroll.data.Attendance;
import com.example.payroll.data.AttendanceDao;
import com.example.payroll.data.Date1;
import com.example.payroll.data.DateDao;
import com.example.payroll.data.EmpDetailsDao;
import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.data.EmpMonthlyClosBalDao;
import com.example.payroll.data.EmpTotalBalance;
import com.example.payroll.data.EmpTotalBalanceDao;
import com.example.payroll.data.Payments;
import com.example.payroll.data.PaymentsDao;
import com.example.payroll.data.Report;
import com.example.payroll.data.ReportDao;
import com.example.payroll.data.empdata;

@Database(entities  ={empdata.class, Date1.class, Attendance.class, Payments.class, EmpMonthlyClosBal.class, EmpTotalBalance.class, Report.class},version = 2)
public abstract class EmployeeDatabase extends RoomDatabase {

    private  static EmployeeDatabase instance;
    public  abstract EmpDetailsDao empDetailsDao();
    public  abstract DateDao dateDao();
    public  abstract AttendanceDao attendanceDao();
    public  abstract PaymentsDao paymentsDao();
    public  abstract EmpMonthlyClosBalDao empMonthlyClosBalDao();
    public  abstract EmpTotalBalanceDao empTotalBalanceDao();
    public  abstract ReportDao reportDao();

    public  static  synchronized  EmployeeDatabase getInstance(Context context)
    {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EmployeeDatabase.class, "emplo_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

