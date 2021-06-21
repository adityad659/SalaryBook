package com.example.payroll.data

import androidx.room.ColumnInfo

class ReportRecordObject(
         @ColumnInfo(name = "SUM(calcsalary)")
        var calcsalary: Float =0F,
         @ColumnInfo(name = "SUM(payment)")
        var payment: Float = 0F,
         @ColumnInfo(name = "SUM(lastClosingBalance)")
        var lastClosingBalance: Float = 0F,
         @ColumnInfo(name = "SUM(closingBalance)")
        var closingBalance: Float = 0F

)
