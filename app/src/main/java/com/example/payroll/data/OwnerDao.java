package com.example.payroll.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface OwnerDao {
    @Insert
    void insert(Ownerd owner);

    @Update
    void update(Ownerd owner);

    @Delete
    void delete(Ownerd owner);
    @Query("SELECT * from owner_table where phno = :pno ")
   LiveData<Ownerd> getOwner(String pno);

    @Transaction
    @Query("SELECT * from owner_table where phno = :pno ")
    Ownerd getOwner1(String pno);


}
