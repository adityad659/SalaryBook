package com.example.payroll;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payroll.AddStaff.AddStaff;
import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Attendance;
import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.data.EmpTotalBalance;
import com.example.payroll.data.Ownerd;
import com.example.payroll.data.empdata;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import static com.example.payroll.GlobalVariable.Owner_nm;
import static com.example.payroll.GlobalVariable.mob_no;
import static com.example.payroll.GlobalVariable.*;

public class StaffFragment extends Fragment {
    RecyclerView recyclerView;
String m;
    SpannableString ss;
    ArrayList<DataStaff> dataholder;
    StaffAdapter adapter;
    Button buttonAddStaff;
    TextView total_calc, report, tot, ownername;
    private MainActivityViewModel mainActivityViewModel;
    Float totalCal;
        private VerifyOTPviewModel verifyOTPviewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_staff, container, false);

        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);

        Ownerd data = verifyOTPviewModel.getGetRecord();
      total_calc = view.findViewById(R.id.total_calc);
        report = view.findViewById(R.id.report);
        tot = view.findViewById(R.id.tot);
        ownername = view.findViewById(R.id.owner_name);
        ownername.setText(data.getBusinessName());
        buttonAddStaff = view.findViewById(R.id.buttonAddStaff);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        LocalDate date = LocalDate.now();

        String s = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("dd MMM");

        m= LocalDate.now().format(formatter5);


        staffSalCalc();




        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Report.class);
                startActivity(intent);
            }
        });
        buttonAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddStaff.class);
                startActivity(intent);
            }
        });
        return view;
    }



    private void staffSalCalc() {
         totalCal = 0F;

        dataholder = new ArrayList<>();
        //inserting employee data into dataholder
        List<empdata> emprecords = mainActivityViewModel.getAllEmpData();

        for (empdata i : emprecords) {
            staffSalCalc1(i);
        }

        adapter = new StaffAdapter(dataholder, getActivity());
        recyclerView.setAdapter(adapter);


        if (totalCal >= 0) {
            tot.setText("Total Advance");
                    total_calc.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));


        } else {
            tot.setText("Total Pending");
                    total_calc.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));

        }
        String totals = numToCurr(totalCal);
        total_calc.setText(totals);
    }
    private void staffSalCalc1(empdata i) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMMM yyyy");

        String y= LocalDate.parse(i.getDate(), formatter).format(formatter1);
        String yz= LocalDate.parse(i.getDate(), formatter).format(formatter);


        String ym = dateToString(LocalDate.now(), "MMM");      //ex:-ym=Mar


        LocalDate r1 = LocalDate.now().plusMonths(1);
       String rm = dateToString(r1, "MMMM yyyy");//--ex:-April 2021

        Float t=0F;
        Float lastClosBal = 0F;
        Float closBal = 0F;

        while (!y.equals(rm)) {
            LocalDate date1= LocalDate.parse(yz,formatter);
            String  monthYear=  dateToString(date1, "M-yyyy");


            Integer actualSalary=i.getSalary() ;
            Integer presentCount=mainActivityViewModel.getPresentCount(monthYear,i.getMobno());
            Integer halfDayCount=mainActivityViewModel.getHalfdayCount(monthYear,i.getMobno());

            Float totalMonthPaymentCalc =0F;
           if(mainActivityViewModel.getMonthTotalPayments(monthYear,i.getMobno())==null) {
                totalMonthPaymentCalc = 0F;
           }
           else{
               totalMonthPaymentCalc=mainActivityViewModel.getMonthTotalPayments(monthYear,i.getMobno());
           }
           Integer  ym1= Integer.parseInt(dateToString(date1, "M"));
            Integer  ym2= Integer.parseInt(dateToString(date1, "yyyy"));

            YearMonth yearMonthObject = YearMonth.of(ym2, ym1);
            Integer daysInMonth = yearMonthObject.lengthOfMonth();



            Integer perDaySalary=  actualSalary/daysInMonth;

            Integer totalPresentDaySalaryCalc=perDaySalary*presentCount;
            Integer totalHalfDaySalaryCalc=(perDaySalary/2)*halfDayCount;
            Integer totalSalCalc=totalHalfDaySalaryCalc+totalPresentDaySalaryCalc;




            closBal=(totalMonthPaymentCalc-totalSalCalc)+lastClosBal;
            t=closBal;

            EmpMonthlyClosBal empMonthlyClosBal=new EmpMonthlyClosBal(i.getMobno(),monthYear,i.getEmpName(),actualSalary,presentCount,
                    halfDayCount,totalSalCalc,totalMonthPaymentCalc,lastClosBal,closBal);
            mainActivityViewModel.insertOrUpdateEmpMonthlyClosBal(empMonthlyClosBal);


            lastClosBal=t;




             r1 = date1.plusMonths(1);

            y = dateToString(r1, "MMMM yyyy");
            yz=r1.format(formatter);

        }
        EmpTotalBalance empTotalBalance =new EmpTotalBalance(i.getMobno(),i.getEmpName(),t, mob_no,Owner_nm);
        mainActivityViewModel.insertOrUpdateEmpTotalBal(empTotalBalance);

        LocalDate date = LocalDate.now();

        String s = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        List<Attendance> attendance = mainActivityViewModel.getAttendanceList(s);
        DataStaff obj = null;
        for(Attendance o:attendance){
            if(o.getMobno().equals(i.getMobno())){
                 obj = new DataStaff(i.getEmpName(),m+" : " +o.getAttendance(), i.getMobno(), "0", null);
            }
        }


        totalCal = totalCal+t;
        obj.setAmount(t);
        dataholder.add(obj);

    }

    private String dateToString(LocalDate date1, String m) {

        return date1.format(DateTimeFormatter.ofPattern(m));
    }

    @Override
    public void onResume() {

        super.onResume();
        staffSalCalc();

    }
}