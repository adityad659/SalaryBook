package com.example.payroll.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DateDao {
    @Insert
    void insert(Date1 date1);

    @Delete
    void delete(Date1 date1);

    @Query("SELECT COUNT(*) from Date_table where date == :date ")
    Boolean getDate(String date);
}
