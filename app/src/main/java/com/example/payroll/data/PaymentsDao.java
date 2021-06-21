package com.example.payroll.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaymentsDao {
    @Insert
    void insert(Payments payments);


  @Query("UPDATE PaymentsTable SET  description =:description," +
          "date=:date,advance=:advance,pending=:pending WHERE payID =:payID ")
    void update(String description,String date,Float advance,Float pending,Integer payID);


  @Query("SELECT * from PaymentsTable where  monthYear = :monthYear and mobno =:mobno")
  List<Payments> getMonthPayments(String monthYear,String mobno);

    @Query("SELECT * from PaymentsTable where payID =:payID")
    Payments getPayment(Integer payID);


    @Query("SELECT SUM(advance)+SUM(pending) from PaymentsTable where  monthYear = :monthYear and mobno = :mobno")
    Float getMonthTotalPayments(String monthYear,String mobno);

    @Query("SELECT SUM(advance)-SUM(pending) from PaymentsTable where mobno = :mobno")
    Float getTotalEmpPayments(String mobno);

    @Query("SELECT SUM(advance)-SUM(pending) from PaymentsTable where ownerMobno = :ownerMobno")
    Float getTotalPayments(String ownerMobno);


}

