package com.example.payroll.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.payroll.GlobalVariable


@Entity(tableName = "EmpMonthlyClosBal_Table")
data class  EmpMonthlyClosBal(
        var mobno: String? = null,
        var monthYear:String?=null,
        var empName: String? = null,
        var actualSalary: Int? = null,
        var presents: Int = 0,
        var halfDay: Int = 0,
        var calcsalary: Float = 0F,
        var payment: Float = 0F,
        var lastClosingBalance: Float? = null,
        var closingBalance: Float? = null,
        ){
        @PrimaryKey(autoGenerate = true)
        var EmpMonthlyClosBalID: Int? = null
        var ownerMobno: String = GlobalVariable.mob_no

}
