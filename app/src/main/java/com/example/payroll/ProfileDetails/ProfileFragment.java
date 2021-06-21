package com.example.payroll.ProfileDetails;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.payroll.GlobalVariable;
import com.example.payroll.R;
import com.example.payroll.SendOTPActivity;
import com.example.payroll.ViewModel.VerifyOTPviewModel;
import com.example.payroll.data.Ownerd;
import com.example.payroll.util.SessionManagement;

public class ProfileFragment extends Fragment   {
    private TextView mTextView;
    Ownerd data;
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 1) {

                        Intent data1 = result.getData();

                        data.setOwnerName(data1.getStringExtra("ownerName"));
                        verifyOTPviewModel.update(data);
                    }
                    else if (result.getResultCode() == 2) {

                        Intent data1 = result.getData();

                        data.setBusinessName(data1.getStringExtra("businessName"));
                        verifyOTPviewModel.update(data);
                    }
                }
            });




    RelativeLayout yourName, businessName;
    private VerifyOTPviewModel verifyOTPviewModel;

    TextView ownerNm,businessNm, mobno;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        verifyOTPviewModel = ViewModelProviders.of(this).get(VerifyOTPviewModel.class);
        data=verifyOTPviewModel.getGetRecord();


//relative layouts id
        yourName = v.findViewById(R.id.YourNameInput);
         businessName=v.findViewById(R.id.BusinessNameInput);
        RelativeLayout passChng= v.findViewById(R.id.pass_chang);
//text view id
        mobno= v.findViewById(R.id.mobno);
        ownerNm= v.findViewById(R.id.owner_nm);
        businessNm= v.findViewById(R.id.bussiness_nm);
        verifyOTPviewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<Ownerd>() {
            @Override
            public void onChanged(Ownerd ownerd) {
                ownerNm.setText(ownerd.getOwnerName());
                businessNm.setText(ownerd.getBusinessName());
                mobno.setText("+91-"+GlobalVariable.mob_no);
                data=ownerd;
            }
        });




        Button logOut=v.findViewById(R.id.logout_btn);
RelativeLayout fingerinp=v.findViewById(R.id.fingerprint_inp);


        fingerinp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       //if the fingerprint data is true then open BottomSheetFingerprint
                if(data.getIsfingerprint()==true) {

                        BottomSheetFingerprint bottomSheet = new BottomSheetFingerprint();
                        bottomSheet.show(getFragmentManager(), "exampleBottom");

                        return;

                }




                androidx.biometric.BiometricManager biometricManager= androidx.biometric.BiometricManager.from(getActivity());
                switch (biometricManager.canAuthenticate()){
                    case  BiometricManager.BIOMETRIC_SUCCESS:
                        Toast.makeText(getActivity(), "You can use the fingerprint sensor to login", Toast.LENGTH_SHORT).show();

                        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                .setTitle("Add Fingerprint Lock")
                                .setMessage("Are you sure you want to add Fingerprint lock")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent intent = new Intent(getActivity(), Fingerprint_Lock.class);
                                        startActivity(intent);

                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();


                        break;
                    case  BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Toast.makeText(getActivity(), "The device don't have a fingerprint sensor", Toast.LENGTH_SHORT).show();
                        break;
                    case  BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(getActivity(), "The biometric sensors is currently unavailable", Toast.LENGTH_SHORT).show();

                        break;
                    case  BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        Toast.makeText(getActivity(), "your device don't have any fingerprint saved , please check your security settings", Toast.LENGTH_SHORT).show();
                        break;
                }




            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                               SessionManagement sessionManagement = new SessionManagement(getActivity());
                               sessionManagement.removeSession();


                               Intent intent = new Intent(getActivity(), SendOTPActivity.class);
                               startActivity(intent);
                               return;
                           }

                })
                .setNegativeButton("No", null)
                .show();

    }
});
        yourName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YourNameEdit.class);
                intent.putExtra("ownerNm",ownerNm.getText().toString().trim());
                someActivityResultLauncher.launch(intent);


            }
        });
        businessName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BusinessNameEdit.class);
                intent.putExtra("businessNm",businessNm.getText().toString().trim());
                someActivityResultLauncher.launch(intent);

            }
        });



        passChng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        if(data.getIspassword()==true) {
                            BottomSheetPassword bottomSheet = new BottomSheetPassword();
                            bottomSheet.show(getFragmentManager(), "exampleBottom");
                            return;
                        }
                        else {
                            Intent intent = new Intent(getActivity(), ConfirmPassword.class);
                            startActivity(intent);
                            return;
                        }
            }
        });

        return v;
    }


}