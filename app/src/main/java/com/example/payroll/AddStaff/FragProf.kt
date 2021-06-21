package com.example.payroll.AddStaff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.payroll.R
import com.nightkyb.listtile.ListTile

class FragProf : AppCompatActivity() {
    private lateinit var listTile: ListTile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag_prof)
        listTile=findViewById(R.id.listTile)
       listTile.setSubtitleText("arfdg")

    }


}