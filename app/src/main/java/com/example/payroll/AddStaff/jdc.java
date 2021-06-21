package com.example.payroll.AddStaff;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payroll.R;
import com.nightkyb.listtile.ListTile;

public class jdc extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_prof);
       ListTile listTile=findViewById(R.id.listTile);
       listTile.setSubtitleText("arjit");
    }
    }
