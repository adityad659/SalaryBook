package com.example.payroll.AddStaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.payroll.Database.DBEmp;
import com.example.payroll.R;
import com.example.payroll.data.empdata;

public class SalaryType extends AppCompatActivity {
    RadioGroup radioGroup;
    DBEmp DB;
    RadioButton radioButton;
    String salaryType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_type);
        radioGroup = findViewById(R.id.radioGroup);
        DB=new DBEmp(this);
        final RadioButton monthly =  findViewById(R.id.monthly);
        final RadioButton perHour =  findViewById(R.id.per_hour_basis);
        final RadioButton daily =  findViewById(R.id.daily);
        final RadioButton workBasis =  findViewById(R.id.work_basis);
        final RadioButton weekly =  findViewById(R.id.weekly);



        Button salaryTypo=findViewById(R.id.salaryTypo);
        salaryTypo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if (monthly.isChecked()) {
                  salaryType  ="monthly";

                }
                else if (perHour.isChecked()) {
                   salaryType ="perHour";

                }
                else if (daily.isChecked()) {
                     salaryType ="daily";
                }
             /*  else if ((workBasis.getId())==radioId) {
                    Intent Intenth = new Intent(getApplicationContext(), .class);
                    startActivity(Intenth);
                }*/
                else if (weekly.isChecked()) {
                     salaryType ="weekly";

                }
               empdata data= getIntent().getParcelableExtra("empdata");
                   data.setSalaryType(salaryType);
                        Intent intents= new Intent(getApplicationContext(), MonthlySalary.class);
                        intents.putExtra("empdata", data);
                        startActivity(intents);
                        return;
                    }


        });
    }

}