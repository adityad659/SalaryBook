package com.example.payroll.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Report_table")
data class Report(
        var ownerMobno: String? = null,
        var ownerName: String? = null,
        var monthYear: String? = null,
        var calcsalary: Float? = null,
        var payment: Float? = null,
        var lastClosingBalance: Float? = null,
        var closingBalance: Float? = null,

        ){
    @PrimaryKey(autoGenerate = true)
    var reportID: Int? = null
}
