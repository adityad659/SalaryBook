package com.example.payroll.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface EmpTotalBalanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    void insert(EmpTotalBalance empTotalBalance);

}
