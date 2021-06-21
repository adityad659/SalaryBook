package com.example.payroll.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "AttendanceTable", indices =[Index(value = ["mobno", "date"], unique = true)])

data class Attendance(

        var empName: String? = null,
        var mobno: String? = null,
        var date: String? = null,
        var monthYear: String? = null,
        var attendance: String? = null,
        var ownerMobno: String? = null,
) {
    @PrimaryKey(autoGenerate = true)

    var attendID: Int? = null
}
