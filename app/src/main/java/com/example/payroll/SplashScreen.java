package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.payroll.ProfileDetails.Fingerprint_Lock;
import com.example.payroll.ProfileDetails.PasswordEdit;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;
import com.example.payroll.util.SessionManagement;

public class SplashScreen extends AppCompatActivity  {
    private VerifyOTPviewModel verifyOTPviewModel;
    Ownerd data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        SessionManagement sessionManagement = new SessionManagement(this);
        String userMobno = sessionManagement.getSession();

        if(!userMobno.equals("-1")){
            GlobalVariable.mob_no=userMobno;
            verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);
            data=verifyOTPviewModel.getGetRecord();
                  GlobalVariable.Owner_nm=  data.getOwnerName();
                }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                    if(userMobno.equals("-1")) {
                        Intent intent = new Intent(getApplicationContext(), SendOTPActivity.class);
                        startActivity(intent);
                        return;
                    }

                                if(data.getIsfingerprint()==true){
                                    Intent intent=new Intent(getApplicationContext(), Fingerprint_Lock.class);
                                    startActivity(intent);
                                    return;
                                }
                                else if(data.getIspassword()==true) {
                                    Intent intent=new Intent(getApplicationContext(), PasswordEdit.class);
                                    startActivity(intent);
                                    return;
                                }
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                return;

            }
        },2000);

    }
}