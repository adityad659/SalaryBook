package com.example.payroll.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmpDetailsDao {
    @Insert
     void insert(empdata emp);

    @Update
    void update(empdata emp);

    @Delete
    void delete(empdata emp);

    @Query("SELECT COUNT(*) from Emp_table where mobno == :mobno ")
    Boolean empExist(String mobno);

    @Query("SELECT * from emp_table where ownerMobno = :pno ")
    List<empdata> getAllEmp(String pno);

    @Transaction
    @Query("SELECT * from emp_table where mobno = :pno ")
    empdata getEmp1(String pno);
}
