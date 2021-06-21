package com.example.payroll.BindingAdapters;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.NumberFormat;
import java.util.Locale;

public class StaffBinding {
    @BindingAdapter("android:numToCurrency")
    public static void numToCurrency(TextView textView, Float amount) {

        String COUNTRY = "IN";
        String LANGUAGE = "en";
        textView.setText(NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(amount));
//        if(amount>=0){
//            textView.setTextColor(ContextCompat.getColor(ContentProviderCompat.requireContext(), R.color.green));
//            return;
//        }
//        total_calc.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));

    }


}
