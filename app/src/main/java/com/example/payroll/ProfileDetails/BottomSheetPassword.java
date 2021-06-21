package com.example.payroll.ProfileDetails;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.payroll.R;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetPassword extends BottomSheetDialogFragment {
    private VerifyOTPviewModel verifyOTPviewModel;
    Ownerd data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_bottom_sheet_password, container, false);

        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);

            data = verifyOTPviewModel.getGetRecord();


        Button button1 = v.findViewById(R.id.Delete_Password);
        Button button2 = v.findViewById(R.id.Edit_Password);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                       data.setIspassword(false);
                       data.setPassword(null);
                       verifyOTPviewModel.update(data);



                       dismiss();




            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Button 2 clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ConfirmPassword.class);
                startActivity(intent);

            }
        });
        return v;
    }


}