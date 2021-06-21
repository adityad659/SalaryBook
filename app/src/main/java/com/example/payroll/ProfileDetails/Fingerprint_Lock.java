package com.example.payroll.ProfileDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.example.payroll.MainActivity;
import com.example.payroll.R;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;

import java.util.concurrent.Executor;

public class Fingerprint_Lock extends AppCompatActivity {
    private VerifyOTPviewModel verifyOTPviewModel;
    Ownerd data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint__lock);
        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);

            data = verifyOTPviewModel.getGetRecord();

        Executor executor= ContextCompat.getMainExecutor(this);
        androidx.biometric.BiometricPrompt biometricPrompt=new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);


                       data.setIsfingerprint(true);
                       verifyOTPviewModel.update(data);

                       if(data.getIspassword()==true) {
                           Intent intent=new Intent(getApplicationContext(),PasswordEdit.class);
                           startActivity(intent);
                           return;
                       }
                       else {
                           Intent intent = new Intent(Fingerprint_Lock.this, MainActivity.class);
                           startActivity(intent);
                       }
                       return;
                   }











            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        BiometricPrompt.PromptInfo promptInfo=new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Use your fingerprint to login to your app")
                .setNegativeButtonText("Cancel")
                .build();
        biometricPrompt.authenticate(promptInfo);
    }
}