package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;


import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.Payments;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class staff_payments extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextInputEditText payDate;
    private TextInputLayout salaryAmount;
    private TextInputLayout salaryDescription;
    RadioButton youPaid, youTook;
    String mob1, date;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_payments);
        payDate = findViewById(R.id.pay_date);
        salaryAmount = findViewById(R.id.salary_amount);
        salaryDescription = findViewById(R.id.salary_description);
        youPaid = findViewById(R.id.you_paid);
        youTook = findViewById(R.id.you_took);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mob1 = getIntent().getStringExtra("mob");
        Calendar c = Calendar.getInstance();
        String m = new SimpleDateFormat("dd,M yyyy").format(c.getTime());
        payDate.setText(m);
        date = m;
        payDate.setInputType(InputType.TYPE_NULL);

        payDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payDate.setInputType(InputType.TYPE_NULL);

                showDatePickerDialog();
            }
        });
    }

    private boolean validatesalaryAmount() {
        String yournameInput = salaryAmount.getEditText().getText().toString().trim();
        if (yournameInput.isEmpty()) {
            salaryAmount.setError("This Field is required");
            return false;
        } else {
            salaryAmount.setError(null);
            return true;
        }
    }

    private boolean validatesalaryDescription() {
        String yournameInput = salaryDescription.getEditText().getText().toString().trim();
        if (yournameInput.isEmpty()) {
            salaryDescription.setError("This Field is required");
            return false;
        } else {
            salaryDescription.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validatesalaryAmount() | !validatesalaryDescription()) {
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd,M yyyy");
        LocalDate date1 = LocalDate.parse(date, formatter);

        Float salaryAmoun = Float.parseFloat(salaryAmount.getEditText().getText().toString().trim());
        String salaryDescriptio = salaryDescription.getEditText().getText().toString().trim();
        String monthYear = dateToString(date1, "M-yyyy");
        Payments data = new Payments(mob1, salaryDescriptio, date, monthYear, 0F, 0F, GlobalVariable.mob_no);
        if (youPaid.isChecked()) {
            data.setAdvance(salaryAmoun);
        } else if (youTook.isChecked()) {
            data.setPending(-salaryAmoun);

        }
        mainActivityViewModel.insertPayment(data);

    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();
    }

    private String dateToString(LocalDate date1, String m) {

        return date1.format(DateTimeFormatter.ofPattern(m));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = dayOfMonth + "," + (month + 1) + " " + year;
        payDate.setText(date);
    }
}