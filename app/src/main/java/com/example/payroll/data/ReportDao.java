package com.example.payroll.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ReportDao {
    @Insert
    void insert(Report report);

    @Query("SELECT COUNT(*) from Report_table where ownerMobno == :ownerMobno and monthYear ==:monthYear ")
    Boolean isRecordExist(String ownerMobno, String monthYear);

    @Query("DELETE from Report_table where ownerMobno == :ownerMobno and monthYear ==:monthYear ")
    void delete(String ownerMobno, String monthYear);

    @Query("SELECT * from Report_table where ownerMobno == :ownerMobno and monthYear ==:monthYear ")
    Report getOwnerMonthRecord(String ownerMobno, String monthYear);

}
