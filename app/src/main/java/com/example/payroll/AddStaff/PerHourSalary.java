package com.example.payroll.AddStaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.payroll.Database.DBEmp;
import com.example.payroll.GlobalVariable;
import com.example.payroll.R;
import com.google.android.material.textfield.TextInputLayout;

public class PerHourSalary extends AppCompatActivity {
    private TextInputLayout hourlySalaryOfStaff;
    DBEmp DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_hour_salary);
        DB=new DBEmp(this);
        hourlySalaryOfStaff = findViewById(R.id.hourly_salary_of_staff);
    }
        private boolean validateHourlySalary() {
            String hourlySalary = hourlySalaryOfStaff.getEditText().getText().toString().trim();
            if (hourlySalary.isEmpty()) {
                hourlySalaryOfStaff.setError("This Field is required");
                return false;
            }
            else {
                hourlySalaryOfStaff.setError(null);
                return true;
            }
        }

    public void hourlySalary(View v) {
        if (!validateHourlySalary() ) {
            return;
        }
        Cursor res1 = DB.getdata();
        while(res1.moveToNext()) {
            if (res1.getString(1).equals(GlobalVariable.emp_mob_no)) {
                Toast.makeText(getApplicationContext(), "user exists " + GlobalVariable.emp_mob_no, Toast.LENGTH_SHORT).show();
                Integer hourly = Integer.parseInt(hourlySalaryOfStaff.getEditText().getText().toString().trim());

                String name = res1.getString(0);
                String mobno = res1.getString(1);

                String salaryType = res1.getString(2);
                Integer salary = hourly;
                Integer advance = res1.getInt(4);
                Integer pending = res1.getInt(5);
                Boolean checkinsertdata = DB.updateuserdata(name, mobno, salaryType, salary, advance, pending);
                if (checkinsertdata == true)
                    Toast.makeText(getApplicationContext(), "business details updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "business details not updated", Toast.LENGTH_SHORT).show();


                String input = "Hourly Salary: " + hourlySalaryOfStaff.getEditText().getText().toString();

                Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), OpeningBalance.class);

                startActivity(intent);
            }

        }
    }

}