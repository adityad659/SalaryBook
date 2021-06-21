package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.payroll.ProfileDetails.ClosingBalanceAdapter;
import com.example.payroll.ProfileDetails.dataClosingBalance;
import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.data.EmpTotalBalance;
import com.example.payroll.data.empdata;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.payroll.GlobalVariable.*;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class Staff_salary_details extends AppCompatActivity {
    RecyclerView recyclerView;
    SpannableString ss;
    TextView mob,total_calc,tot;
    TextView thisMonthPayments,thisMonthSalary,closingBalance,monthSal,monthPay,ownerNm;
    Button addpay;
    LinearLayout thisMonthPayments1,thisMonthSalary1,closingBalance1;
    ClosingBalanceAdapter adapter;
    ArrayList<dataClosingBalance> dataholder;
    String mob1,name,y,ym,yi,rm,rms,cr,cro,currMonthYear;
    Calendar rightNow,c;
    LocalDate r;
    DateTimeFormatter formatter3,formatter4;
    private MainActivityViewModel mainActivityViewModel;
    ArrayList<dataClosingBalance> months;
    empdata empdata1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_salary_details);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        thisMonthPayments1=findViewById(R.id.this_month_payment1);
        thisMonthSalary1=findViewById(R.id.this_month_salary1);
        closingBalance1=findViewById(R.id.closing_balance1);
        addpay = findViewById(R.id.buttonAddPayments);
        ownerNm=findViewById(R.id.owner_name);
monthSal=findViewById(R.id.monthSal);
monthPay=findViewById(R.id.monthPay);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mob1 = getIntent().getStringExtra("mob");
        name = getIntent().getStringExtra("name");

          currMonthYear= dateToString(LocalDate.now(), "M-yyyy");

        //refernce ids of xml
        reference();

        dataholder = new ArrayList<>();

        rightNow= Calendar.getInstance();
        ym =  dateToString(LocalDate.now(), "MMM");      //ex:-ym=Mar
        yi =  dateToString(LocalDate.now(), "M yyyy");   //ex:-3 2021
       //-----

        r= LocalDate.now().minusMonths(1);
        rm = dateToString(r, "MMMM yyyy"); //ex:-rm=February 2021
        rms = dateToString(LocalDate.now(), "MMMM yyyyy"); //ex:-rms=March 2021

//-----will display month_nm Salary (Mar Salary) and month_nm Payments (Mar Payments)
        thisMonthSalary.setText(ym + " Salary");
        thisMonthPayments.setText(ym + " Payments");



         empdata1=mainActivityViewModel.getEmpData(mob1);
         formatter3 = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
         formatter4 = DateTimeFormatter.ofPattern("MMMM yyyy");
ownerNm.setText(empdata1.getEmpName());
       y= empdata1.getDate();

       //gets the date of employee joining

//to display closing balance buttons in recycler view

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M-yyyy");
        LocalDate start =  LocalDate.parse(y,formatter3);
        start=start.with(lastDayOfMonth());
        LocalDate end = LocalDate.now().with(lastDayOfMonth());
         months = new ArrayList<>();
        LocalDate date = end.minusMonths(1);
       LocalDate temp= start.minusMonths(1).with(lastDayOfMonth());
        while (date.isAfter(temp) && !date.isEqual(temp)) {
            dataClosingBalance obj1 = new dataClosingBalance(date.format(formatter),mob1,date.format(formatter1),0);
            months.add(obj1);
            date = date.minusMonths(1).with(lastDayOfMonth());
        }

//------------------





        staffSalCalc();
        adapter=new ClosingBalanceAdapter(months,this);
        recyclerView.setAdapter(adapter);

        thisMonthSalary1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MonthlySalaryDetails.class);
                intent.putExtra("mob", mob1);

                startActivity(intent);
            }
        });
        thisMonthPayments1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MonthlyPaymentDetails.class);
                intent.putExtra("mob", mob1);

                startActivity(intent);
            }
        });
        addpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), staff_payments.class);
                intent.putExtra("mob", mob1);
                startActivity(intent);
            }
        });



    }

    private void staffSalCalc() {
        Float t=0F;
        LocalDate r1;
        Float lastClosBal = 0F;
        float closBal=0;
y=LocalDate.parse(empdata1.getDate(), formatter3).format(formatter4);
      String  yz=LocalDate.parse(empdata1.getDate(), formatter3).format(formatter3);
        LocalDate r3 = LocalDate.now().plusMonths(1);
        String rm = dateToString(r3, "MMMM yyyy");//--ex:-April 2021

        while (!y.equals(rm)) {
            LocalDate date1= LocalDate.parse(yz,formatter3);



            String  monthYear= dateToString(date1, "M-yyyy");


            Integer actualSalary=empdata1.getSalary() ;
            Integer presentCount=mainActivityViewModel.getPresentCount(monthYear,mob1);
            Integer halfDayCount=mainActivityViewModel.getHalfdayCount(monthYear,mob1);

            Float totalMonthPaymentCalc =0F;
            if(mainActivityViewModel.getMonthTotalPayments(monthYear,mob1)==null) {
                totalMonthPaymentCalc = 0F;
            }
            else{
                totalMonthPaymentCalc=mainActivityViewModel.getMonthTotalPayments(monthYear,mob1);
            }

            Integer  ym1= Integer.parseInt( dateToString(date1, "M"));
            Integer  ym2= Integer.parseInt( dateToString(date1, "yyyy"));

            YearMonth yearMonthObject = YearMonth.of(ym2, ym1);
           Integer daysInMonth = yearMonthObject.lengthOfMonth();



            Integer perDaySalary=  actualSalary/daysInMonth;

            Integer totalPresentDaySalaryCalc=perDaySalary*presentCount;
            Integer totalHalfDaySalaryCalc=(perDaySalary/2)*halfDayCount;
            Integer totalSalCalc=totalHalfDaySalaryCalc+totalPresentDaySalaryCalc;



            closBal=(totalMonthPaymentCalc-totalSalCalc)+lastClosBal;
t=closBal;
            if(monthYear.equals(currMonthYear)){
                if(totalSalCalc<0) {
                    monthSal.setTextColor(ContextCompat.getColor(this, R.color.green));
                }
                else{
                    monthSal.setTextColor(ContextCompat.getColor(this, R.color.red));
                }
                monthSal.setText(numToCurr(totalSalCalc));
                if(totalMonthPaymentCalc>=0) {
                    monthPay.setTextColor(ContextCompat.getColor(this, R.color.green));
                }
                else{
                    monthPay.setTextColor(ContextCompat.getColor(this, R.color.red));
                }
                monthPay.setText(numToCurr(totalMonthPaymentCalc));
            }
            for(dataClosingBalance u:months){
                if(monthYear.equals(u.getDate1())){
                    u.setAmount((int) closBal);
                }
            }

            EmpMonthlyClosBal empMonthlyClosBal=new EmpMonthlyClosBal(mob1,monthYear,name,actualSalary,presentCount,
                    halfDayCount,totalSalCalc,totalMonthPaymentCalc,lastClosBal,closBal);
            mainActivityViewModel.insertOrUpdateEmpMonthlyClosBal(empMonthlyClosBal);


            lastClosBal=t;


                if (t >= 0) {
                    tot.setText("Total Advance");
                    total_calc.setTextColor(ContextCompat.getColor(this, R.color.green));
                } else {
                    tot.setText("Total Pending");
                    total_calc.setTextColor(ContextCompat.getColor(this, R.color.red));
                }


            total_calc.setText(numToCurr(t));

            r1 = date1.plusMonths(1);

            y = dateToString(r1, "MMMM yyyy");
            yz=r1.format(formatter3);

        }
        EmpTotalBalance empTotalBalance =new EmpTotalBalance(mob1,name,t, mob_no,Owner_nm);
        mainActivityViewModel.insertOrUpdateEmpTotalBal(empTotalBalance);

    }


    private void reference() {
        thisMonthSalary = findViewById(R.id.this_month_salary);
        thisMonthPayments = findViewById(R.id.this_month_payment);
        closingBalance = findViewById(R.id.closing_balance);
        total_calc=findViewById(R.id.total_calc);
        tot=findViewById(R.id.tot);
    }
    private String dateToString(LocalDate date1, String m) {

        return date1.format(DateTimeFormatter.ofPattern(m));
    }
    @Override
    public void onResume() {

        super.onResume();
        y= empdata1.getDate();
        staffSalCalc();

    }


}