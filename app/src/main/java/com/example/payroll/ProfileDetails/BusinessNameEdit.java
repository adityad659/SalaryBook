package com.example.payroll.ProfileDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.payroll.R;

public class BusinessNameEdit extends AppCompatActivity {
    private EditText textBusinessName;

    private String businessName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_name_edit);
        businessName=getIntent().getStringExtra("businessNm");
        EditText bname=findViewById(R.id.editText);
        bname.setText(businessName);

        textBusinessName=findViewById(R.id.editText);


    }
    private boolean validateYourname() {
        String yournameInput = textBusinessName.getText().toString().trim();
        if (yournameInput.isEmpty()) {
            textBusinessName.setError("This Field is required");
            return false;
        }
        else {
            textBusinessName.setError(null);
            return true;
        }
    }
    public void saveYourName(View v) {

        if (!validateYourname()  ) {
            return;
        }

        businessName=  textBusinessName.getText().toString().trim();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("businessName", businessName);
        setResult(2, resultIntent);


        super.finish();



    }
}