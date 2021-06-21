package com.example.payroll.ProfileDetails;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.payroll.R;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFingerprint extends BottomSheetDialogFragment {
    private VerifyOTPviewModel verifyOTPviewModel;
    Ownerd data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_bottom_sheet_fingerprint, container, false);
        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);

            data = verifyOTPviewModel.getGetRecord();


        Button button1 = v.findViewById(R.id.Delete_Fingerprint);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setIsfingerprint(false);
                verifyOTPviewModel.update(data);

                dismiss();
                return;
            }
        });
return v;
    }
}