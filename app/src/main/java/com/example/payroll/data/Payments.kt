package com.example.payroll.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PaymentsTable")

data class Payments(
        var mobno: String? = null,
        var description: String? = null,
        var date: String? = null,
        var monthYear:String?=null,
        var advance: Float? = null,
        var pending: Float? = null,
        var ownerMobno: String? = null
){
        @PrimaryKey(autoGenerate = true)
        var payID: Int? = null
}


