package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.empdata;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;

import static com.example.payroll.GlobalVariable.numToCurr;

public class MonthlySalaryDetails extends AppCompatActivity {
    String mob1;
    Integer salary;
    TextView monthsal;
    Integer count=0;
    TextView noOfPresents,noOfHalfDays;
    TextView sal1,sal2,halfDayCalc;
    Integer daysInMonth;

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_salary_details);

        noOfPresents=findViewById(R.id.no_Of_Presents);
        noOfHalfDays=findViewById(R.id.no_Of_HalfDays);
        halfDayCalc=findViewById(R.id.half_day_calc);

        mob1=   getIntent().getStringExtra(  "mob");
monthsal=findViewById(R.id.monthsal);
        sal2=findViewById(R.id.sal2);
        sal1=findViewById(R.id.sal1);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        count=0;

        Calendar rightNow = Calendar.getInstance();
        String  ym=  new SimpleDateFormat("M-yyyy").format(rightNow.getTime());
        //----to get number of days in a particular month
        Integer  ym1= Integer.parseInt( new SimpleDateFormat("M").format(rightNow.getTime()));
        Integer  ym2= Integer.parseInt( new SimpleDateFormat("yyyy").format(rightNow.getTime()));
        YearMonth yearMonthObject = YearMonth.of(ym2, ym1);
         daysInMonth = yearMonthObject.lengthOfMonth();



//----get salary from employee table
        empdata data=  mainActivityViewModel.getEmpData(mob1);
        salary=data.getSalary();

        Integer presentCount=mainActivityViewModel.getPresentCount(ym,mob1);
        Integer halfDayCount=mainActivityViewModel.getHalfdayCount(ym,mob1);


      Integer perDaySalary=  salary/daysInMonth;

        Integer totalPresentDaySalaryCalc=perDaySalary*presentCount;
        Integer totalHalfDaySalaryCalc=(perDaySalary/2)*halfDayCount;
Integer totalSalCalc=totalHalfDaySalaryCalc+totalPresentDaySalaryCalc;
        sal1.setText(""+numToCurr(totalPresentDaySalaryCalc));
        sal2.setText(""+numToCurr(totalSalCalc));
        halfDayCalc.setText(""+numToCurr(totalHalfDaySalaryCalc));

        noOfPresents.setText(presentCount+" Presents");
        noOfHalfDays.setText(halfDayCount+" HalfDay");

        monthsal.setText(""+numToCurr(salary));
    }

}