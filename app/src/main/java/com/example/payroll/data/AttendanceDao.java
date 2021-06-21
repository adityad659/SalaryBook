package com.example.payroll.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  AttendanceDao {
    @Insert
    void insert(List<Attendance> attendance);

    @Query("UPDATE AttendanceTable SET attendance = :attendance WHERE mobno =:mobno and date=:date")
   void update(String attendance,String mobno,String date);

    @Query("SELECT * from AttendanceTable where ownerMobno = :ownerMobno and date =:date ")
    List<Attendance> getAttendance(String ownerMobno, String date);

    @Query("SELECT COUNT(*) from AttendanceTable where  monthYear = :monthYear and mobno=:mobno and attendance='present'")
    Integer getMonthPresents(String monthYear,String mobno);

    @Query("SELECT COUNT(*) from AttendanceTable where  monthYear = :monthYear and mobno=:mobno and attendance='halfday'")
    Integer getMonthHalfDay(String monthYear,String mobno);

    @Query("SELECT COUNT(*) from AttendanceTable where ownerMobno = :ownerMobno and date =:date ")
    Boolean attendanceExist(String ownerMobno, String date);


}
