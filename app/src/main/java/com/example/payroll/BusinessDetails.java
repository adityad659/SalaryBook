package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.payroll.Database.DBLog;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;
import com.example.payroll.databinding.ActivityBusinessDetailsBinding;
import com.example.payroll.util.SessionManagement;
import com.example.payroll.util.User;

public class BusinessDetails extends AppCompatActivity {

    DBLog SB;

    ActivityBusinessDetailsBinding binding;
    private VerifyOTPviewModel verifyOTPviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding=ActivityBusinessDetailsBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());

        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);

        SB = new DBLog(this);


        binding.textInputYourName.getEditText().addTextChangedListener(inputTextWatcher);
        binding.textInputBusinessName.getEditText().addTextChangedListener(inputTextWatcher);

    }


    private TextWatcher inputTextWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String yournameInput = binding.textInputYourName.getEditText().getText().toString().trim();
            String businessnameInput = binding.textInputBusinessName.getEditText().getText().toString().trim();


            binding.buttonContinue.setEnabled(!yournameInput.isEmpty() && !businessnameInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public void confirmInput(View v) {
        String phno = getIntent().getStringExtra("mobile");


            Boolean checkinsertdata = SB.insertuserdata(phno);

        String ownerName=  binding.textInputYourName.getEditText().getText().toString();
        String businessName=  binding.textInputBusinessName.getEditText().getText().toString();

GlobalVariable.Owner_nm=ownerName;


                //--------------------------------------------------------------------------------
                Ownerd data=new Ownerd(phno,ownerName,businessName,false,"",false);
                verifyOTPviewModel.insert(data);
        User user = new User(phno);
        SessionManagement sessionManagement = new SessionManagement(this);
        sessionManagement.saveSession(user);

                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return;
            }

}