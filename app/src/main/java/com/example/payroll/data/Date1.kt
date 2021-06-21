package com.example.payroll.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Date_table")
@Parcelize
data class Date1(
        @NonNull
        @PrimaryKey
        var date: String = "0",
        var ownerMobno: String? = null,
): Parcelable
