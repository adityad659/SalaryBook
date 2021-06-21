package com.example.payroll.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmpMonthlyClosBalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insert(EmpMonthlyClosBal empMonthlyClosBal);

    @Query("SELECT COUNT(*) from EmpMonthlyClosBal_Table where mobno == :mobno and monthYear ==:monthYear ")
     Boolean isRecordExist(String mobno, String monthYear);

    @Query("DELETE from EmpMonthlyClosBal_Table where mobno == :mobno and monthYear ==:monthYear ")
     void delete(String mobno, String monthYear);

    @Query("SELECT * from EmpMonthlyClosBal_Table where mobno == :mobno and monthYear ==:monthYear ")
    EmpMonthlyClosBal getEmpMonthRecord(String mobno, String monthYear);

    @Query("SELECT SUM(calcsalary),SUM(payment),SUM(lastClosingBalance),SUM(closingBalance) from EmpMonthlyClosBal_Table where ownerMobno == :ownerMobno and monthYear ==:monthYear ")
   ReportRecordObject getMonthRecordSum(String ownerMobno, String monthYear);

    @Query("SELECT * from EmpMonthlyClosBal_Table where ownerMobno == :ownerMobno and monthYear ==:monthYear ")
    List<EmpMonthlyClosBal> getListEmpMonthRecord(String ownerMobno, String monthYear);


}
