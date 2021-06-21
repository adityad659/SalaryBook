package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.data.ReportRecordObject;
import com.example.payroll.data.empdata;
import com.example.payroll.databinding.ActivityBusinessDetailsBinding;
import com.example.payroll.databinding.ActivityReportBinding;
import com.example.payroll.databinding.ReportRowDesignBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class Report extends AppCompatActivity {
    RecyclerView recyclerView;
    ReportAdapter adapter;
    TextView amount, Feb_salary, Jan_closing_balance, Feb_Payments, pending;
    DateTimeFormatter formatter3,formatter4,formatter5;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityReportBinding activityReportBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReportBinding= ActivityReportBinding.inflate(getLayoutInflater());

        setContentView(activityReportBinding.getRoot());

        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        amount = findViewById(R.id.amount);
        Feb_salary = findViewById(R.id.Feb_salary);
        Jan_closing_balance = findViewById(R.id.Jan_closing_balance);
        Feb_Payments = findViewById(R.id.Feb_Payments);
        pending = findViewById(R.id.pending);

        formatter3 = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
        formatter4 = DateTimeFormatter.ofPattern("MMMM yyyy");
        formatter5 = DateTimeFormatter.ofPattern("M-yyyy");

        activityReportBinding.setMonthValues1( GlobalVariable.monthYearConv(LocalDate.now()));

        List<empdata> emprecords = mainActivityViewModel.getAllEmpData();
        if(!emprecords.isEmpty()) {
            List<LocalDate> dateTimeList = new ArrayList<>();
            for (empdata i : emprecords) {
                dateTimeList.add(LocalDate.parse(i.getDate(), formatter3));
            }

            LocalDate start = Collections.min(dateTimeList);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");

            start = start.with(lastDayOfMonth());
            LocalDate end = LocalDate.now().with(lastDayOfMonth());
            LocalDate date = start;

            while (date.isBefore(end) || date.isEqual(end)) {
                ReportRecordObject obj = mainActivityViewModel.getSumEmpMonthRecord(GlobalVariable.mob_no, date.format(formatter5));
                com.example.payroll.data.Report report =
                        new com.example.payroll.data.Report(GlobalVariable.mob_no, GlobalVariable.Owner_nm,
                                date.format(formatter5), obj.getCalcsalary(), obj.getPayment(),
                                obj.getLastClosingBalance(), obj.getClosingBalance());
                mainActivityViewModel.insertOrUpdateOwnerMonthlyClosBal(report);
                date = date.plusMonths(1).with(lastDayOfMonth());

            }
            List<EmpMonthlyClosBal> empMonthlyClosBalList = mainActivityViewModel.getListEmpMonthRecord(GlobalVariable.mob_no, end.format(formatter5));
            adapter = new ReportAdapter(empMonthlyClosBalList, getApplicationContext());
            recyclerView.setAdapter(adapter);
            com.example.payroll.data.Report report = mainActivityViewModel.getOwnerMonthRecord(GlobalVariable.mob_no, end.format(formatter5));

            if (report.getClosingBalance() > 0) {
                pending.setText("Advance amount");
            } else {
                pending.setText("Pending amount");
            }

            amount.setText("" +GlobalVariable.numToCurr(report.getClosingBalance()));
            Feb_salary.setText("" + GlobalVariable.numToCurr(report.getCalcsalary()));
            Jan_closing_balance.setText("" + GlobalVariable.numToCurr(report.getLastClosingBalance()));
            Feb_Payments.setText("" + GlobalVariable.numToCurr(report.getPayment()));
        }
        else{
            pending.setText("Amount");
            amount.setText("0");
            Feb_salary.setText("0");
            Jan_closing_balance.setText("0");
            Feb_Payments.setText("0");
        }
    }
}