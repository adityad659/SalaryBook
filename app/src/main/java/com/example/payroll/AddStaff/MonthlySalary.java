package com.example.payroll.AddStaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.payroll.Database.DBEmp;
import com.example.payroll.R;
import com.example.payroll.data.empdata;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MonthlySalary extends AppCompatActivity {
    private TextInputLayout monthlySalaryOfStaff;
    DBEmp DB;
    Integer monthlysalary;
TextView staffSal;
    TextInputEditText monthly;
    Button bcontinue;
    empdata data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_salary);
        DB = new DBEmp(this);
         data= getIntent().getParcelableExtra("empdata");
        String salaryType = data.getSalaryType();
        monthlySalaryOfStaff = findViewById(R.id.monthly_salary_of_staff);
        staffSal = findViewById(R.id.staffSal);
        bcontinue = findViewById(R.id.buttonContinue);

        monthly = findViewById(R.id.monthly);
        switch (salaryType) {
            case "monthly":
                staffSal.setText("Please add staff\'s Monthly Salary");
                monthlySalaryOfStaff.setHint("Monthly Salary of Staff");
                break;
            case "perHour":
                staffSal.setText("Please add staff\'s hourly Salary");
                monthlySalaryOfStaff.setHint("Salary (Per Hour)");
                break;
            case "daily":
            case "weekly":
                staffSal.setText("Please add staff\'s Daily Salary");
                monthlySalaryOfStaff.setHint("Daily Salary of Staff");
                break;
        }
        monthlySalaryOfStaff.getEditText().addTextChangedListener(inputTextWatcher);

    }
        private TextWatcher inputTextWatcher =new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String month=monthlySalaryOfStaff.getEditText().getText().toString().trim();
                bcontinue.setEnabled(!month.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };



    public void monthlySalary(View v) {


                Integer salary =Integer.parseInt(monthly.getText().toString().trim());
        data.setSalary(salary);

        Intent intent = new Intent(getApplicationContext(), OpeningBalance.class);
        intent.putExtra("empdata", data);
        startActivity(intent);


    }


}