package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.data.Payments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.payroll.GlobalVariable.numToCurr;

public class ClosingBalanceDetails extends AppCompatActivity {
    String mob1;
    String month;
    TextView febSalary, Jan_closing_balance, Feb_Payments, feb_pay, Feb_closing_balance, monthly_salary, present,halfDay, sal1,sal2,sal3, tot;
    TextView m1, m2, m3, m4, m5;

    Date date1;

    RecyclerView recyclerView;

    PaymentsAdapter adapter;

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closing_balance_details);
        mob1 = getIntent().getStringExtra("mob");
        month = getIntent().getStringExtra("month");

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        febSalary = findViewById(R.id.Feb_salary);
        m1 = findViewById(R.id.m1);
        m2 = findViewById(R.id.m2);
        m3 = findViewById(R.id.m3);
        m4 = findViewById(R.id.m4);
        m5 = findViewById(R.id.m5);
        tot = findViewById(R.id.tot);
        Jan_closing_balance = findViewById(R.id.Jan_closing_balance);
        Feb_Payments = findViewById(R.id.Feb_Payments);
        Feb_closing_balance = findViewById(R.id.Feb_closing_balance);
        monthly_salary = findViewById(R.id.monthly_salary);
        present = findViewById(R.id.present);
        halfDay = findViewById(R.id.half_day);

        sal1 = findViewById(R.id.sal1);
        sal2 = findViewById(R.id.sal2);
        sal3 = findViewById(R.id.sal3);

        feb_pay = findViewById(R.id.feb_pay);

        EmpMonthlyClosBal empMonthlyClosBal = mainActivityViewModel.getEmpMonthRecord(mob1,month);


        try {
            date1 = new SimpleDateFormat("M-yyyy").parse(month);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        Integer  ym1= Integer.parseInt( new SimpleDateFormat("M").format(date1.getTime()));
        Integer  ym2= Integer.parseInt( new SimpleDateFormat("yyyy").format(date1.getTime()));
        YearMonth yearMonthObject = YearMonth.of(ym2, ym1);
       Integer daysInMonth = yearMonthObject.lengthOfMonth();

        String date2 = new SimpleDateFormat("MMM").format(date1.getTime());
        String ym = new SimpleDateFormat("M-yyyy").format(date1.getTime());

        m1.setText(date2 + " Salary");
        m3.setText(date2 + " Payments");
        m4.setText(date2 + " Closing Balance");
        m5.setText(date2 + " Salary");
        feb_pay.setText(date2 + " Payments");

        Calendar cw = Calendar.getInstance();
        cw.setTime(date1);
        cw.add(Calendar.MONTH, -1);
        date1 = cw.getTime();
        date2 = new SimpleDateFormat("MMM").format(date1.getTime());


        m2.setText(date2 + " Closing Balance");
        Integer perDaySalary=  empMonthlyClosBal.getActualSalary()/daysInMonth;
        Integer totalPresentDaySalaryCalc=perDaySalary*empMonthlyClosBal.getPresents();
        Integer totalHalfDaySalaryCalc=(perDaySalary/2)*empMonthlyClosBal.getHalfDay();

        febSalary.setText(""+numToCurr(empMonthlyClosBal.getCalcsalary()));
        Jan_closing_balance.setText(""+numToCurr(empMonthlyClosBal.getLastClosingBalance()));
        Feb_Payments.setText(""+numToCurr(empMonthlyClosBal.getPayment()));
        Feb_closing_balance.setText(""+numToCurr(empMonthlyClosBal.getClosingBalance()));
        monthly_salary.setText(""+numToCurr(empMonthlyClosBal.getActualSalary()));
        present.setText(empMonthlyClosBal.getPresents()+" Presents");
        halfDay.setText(empMonthlyClosBal.getHalfDay()+" HalfDays");

        sal1.setText(""+numToCurr(empMonthlyClosBal.getCalcsalary()));
        sal2.setText(""+numToCurr(totalPresentDaySalaryCalc));
        sal3.setText(""+numToCurr(totalHalfDaySalaryCalc));

        tot.setText(""+numToCurr(empMonthlyClosBal.getPayment()));

        List<Payments> payments= mainActivityViewModel.getMonthPaymentsList(ym,mob1);



        adapter = new PaymentsAdapter(payments, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}