package com.example.payroll.AddStaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.payroll.GlobalVariable;
import com.example.payroll.R;
import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.empdata;
import com.google.android.material.textfield.TextInputLayout;

public class AddStaff extends AppCompatActivity {
    private TextInputLayout staffFullName;
    private TextInputLayout staffMobileNumber;
    String staffmobInput;
    String staffnameInput;
    Button bcontinue;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        staffFullName=findViewById(R.id.staff_full_name);
        staffMobileNumber=findViewById(R.id.staff_mobile_number);
        bcontinue=findViewById(R.id.buttonContinue);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        staffFullName.getEditText().addTextChangedListener(inputTextWatcher);
        staffMobileNumber.getEditText().addTextChangedListener(inputTextWatcher);

    }
    private TextWatcher inputTextWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            staffnameInput = staffFullName.getEditText().getText().toString().trim();
            staffmobInput = staffMobileNumber.getEditText().getText().toString().trim();
bcontinue.setEnabled(!staffnameInput.isEmpty() && !staffmobInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };




    public void Continue1(View v) {

        staffnameInput = staffFullName.getEditText().getText().toString().trim();
        staffmobInput = staffMobileNumber.getEditText().getText().toString().trim();


                if (mainActivityViewModel.empExist(staffmobInput)) {
                    Toast.makeText(this, "user exists " + staffmobInput, Toast.LENGTH_SHORT).show();
                    return;
                }




                GlobalVariable.emp_mob_no=staffmobInput;
        empdata data=new empdata(staffnameInput,staffmobInput," ",0,0,0,"",GlobalVariable.mob_no,GlobalVariable.Owner_nm);

                 Intent intent = new Intent(getApplicationContext(), SalaryType.class);
                 intent.putExtra("empdata", data);
                 startActivity(intent);


    }

}