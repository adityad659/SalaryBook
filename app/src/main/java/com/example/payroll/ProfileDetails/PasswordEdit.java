package com.example.payroll.ProfileDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.payroll.MainActivity;
import com.example.payroll.R;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;
import com.hanks.passcodeview.PasscodeView;

public class PasswordEdit extends AppCompatActivity {
PasscodeView passcodeView;
    String pass;
    Ownerd data;
    private VerifyOTPviewModel verifyOTPviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_edit);
        passcodeView=findViewById(R.id.passcode_view);

        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);

            data = verifyOTPviewModel.getGetRecord();



        pass=data.getPassword();




        passcodeView.setLocalPasscode(pass)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    passcodeView.setListener(new PasscodeView.PasscodeViewListener() {
                @Override
                public void onFail() {
                    Toast.makeText(PasswordEdit.this, "Password is Wrong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(String number) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
    }
}