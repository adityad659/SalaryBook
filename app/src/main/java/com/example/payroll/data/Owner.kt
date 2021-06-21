package com.example.payroll.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Owner_table")
@Parcelize
data class Ownerd (
        @PrimaryKey
        var phno: String = "0",
        var ownerName: String? = null,
        var businessName: String? = null,
        var ispassword: Boolean? = false,
        var password: String? = null,
        var isfingerprint: Boolean? = false
): Parcelable
