package com.example.payroll.AddStaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.payroll.GlobalVariable;
import com.example.payroll.MainActivity;
import com.example.payroll.R;
import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.Attendance;
import com.example.payroll.data.Payments;
import com.example.payroll.data.empdata;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OpeningBalance extends AppCompatActivity {
    private TextInputLayout salaryAmount;
    RadioGroup radioGroup;

    RadioButton radioButton;
     RadioButton youPaid;
     RadioButton youTook;
     Button bcontinue;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_balance);

        salaryAmount = findViewById(R.id.salary_amount);
         youPaid = findViewById(R.id.you_paid);
        youTook = findViewById(R.id.you_took);
        radioGroup = findViewById(R.id.radioGroup);
        salaryAmount.getEditText().addTextChangedListener(inputTextWatcher);
        bcontinue = findViewById(R.id.buttonContinue);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


    }
    private TextWatcher inputTextWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String month=salaryAmount.getEditText().getText().toString().trim();
            bcontinue.setEnabled(!month.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


        public void SalAmount(View v) {

            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);

            empdata data= getIntent().getParcelableExtra("empdata");


           Float bal = Float.parseFloat(salaryAmount.getEditText().getText().toString().trim());


            Calendar rightNow = Calendar.getInstance();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
            String  date= LocalDate.now().format(formatter);
            String monthYear = new SimpleDateFormat("M-yyyy").format(rightNow.getTime());

            data.setDate(date);

                    if (youPaid.isChecked()) {
                        data.setAdvance(bal);
                        data.setPending(0);

                    } else if (youTook.isChecked()) {
                        data.setAdvance(0);
                        data.setPending(-bal);

                    }
                    data.setOwnerMobno(GlobalVariable.mob_no);
                    data.setOwnerName(GlobalVariable.Owner_nm);
                    //------------insert employee data
            mainActivityViewModel.insertEmployee(data);

            //------------insert payment data
            Payments payments=new Payments(data.getMobno(),"Amount till date",data.getDate(),monthYear,data.getAdvance(),data.getPending(), GlobalVariable.mob_no);
            mainActivityViewModel.insertPayment(payments);

            Date date1 = Calendar.getInstance().getTime();
            String dat = DateFormat.getDateInstance(DateFormat.LONG).format(date1);

            //------------insert attendance data
            List<Attendance> attendance = new ArrayList<>();
            Attendance attendance1 = new Attendance(data.getEmpName(), data.getMobno(), dat, monthYear, "present", GlobalVariable.mob_no);
                attendance.add(attendance1);

            mainActivityViewModel.insertAttendance(attendance);


//--------go to main activity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

        }
}