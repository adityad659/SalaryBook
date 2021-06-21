package com.example.payroll.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Emp_table")
@Parcelize
data class empdata (
        var empName: String? = null,
        @NonNull
        @PrimaryKey
        var mobno: String = "0",
        var salaryType: String? = null,
        var salary: Int = 0,
        var advance: Float = 0F,
        var pending: Float = 0F,
        var date: String? = null,
        var ownerMobno: String? = null,
        var ownerName: String? = null,

        ):Parcelable
