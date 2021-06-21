package com.example.payroll.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EmpTotalBalance_table")

data class EmpTotalBalance(
        @PrimaryKey
        var mobno: String = "0",
        var empName: String? = null,
        var balance: Float = 0F,
        var ownerMobno: String? = null,
        var ownerName: String? = null,
        )
