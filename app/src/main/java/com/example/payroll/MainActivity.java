package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;


import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.Attendance;
import com.example.payroll.data.Date1;
import com.example.payroll.data.empdata;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController =navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        Date date = Calendar.getInstance().getTime();

        String r = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
        String monthYear = new SimpleDateFormat("M-yyyy").format(date);
        String dat = DateFormat.getDateInstance(DateFormat.LONG).format(date);

        samp:
        {

            if (mainActivityViewModel.getDate(r) == true) {
                break samp;
            }

            Date1 date1 = new Date1(r, GlobalVariable.mob_no);
            mainActivityViewModel.insert(date1);


            List<empdata> emprecords = mainActivityViewModel.getAllEmpData();
            List<Attendance> attendance = new ArrayList<>();

            for (empdata i : emprecords) {
                Attendance attendance1 = new Attendance(i.getEmpName(), i.getMobno(), dat, monthYear, "present", GlobalVariable.mob_no);
                attendance.add(attendance1);
            }
            mainActivityViewModel.insertAttendance(attendance);

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }


}