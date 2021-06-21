package com.example.payroll.ProfileDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.payroll.MainActivity;
import com.example.payroll.R;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;
import com.hanks.passcodeview.PasscodeView;

public class ConfirmPassword extends AppCompatActivity {
    PasscodeView passcodeView;
    Ownerd data;
    private VerifyOTPviewModel verifyOTPviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);

            data = verifyOTPviewModel.getGetRecord();


        passcodeView=findViewById(R.id.passwo_inp);
        passcodeView.setPasscodeLength(4);
        passcodeView.getLocalPasscode();

        passcodeView.setListener(new PasscodeView.PasscodeViewListener() {

               @Override
    public void onFail() {
        Toast.makeText(ConfirmPassword.this, "Password is Wrong", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(String number) {
String password=passcodeView.getLocalPasscode();


               data.setIspassword(true);
               data.setPassword(password);
               verifyOTPviewModel.update(data);


               Intent intent=new Intent(getApplicationContext(), MainActivity.class);

               startActivity(intent);
               return;
           }





});
    }
}