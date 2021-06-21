package com.example.payroll.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.payroll.Repository.EmployeeRepository;
import com.example.payroll.Repository.PaymentsRepository;
import com.example.payroll.data.Attendance;
import com.example.payroll.data.Date1;
import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.data.EmpTotalBalance;
import com.example.payroll.data.Payments;
import com.example.payroll.data.Report;
import com.example.payroll.data.ReportRecordObject;
import com.example.payroll.data.empdata;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private EmployeeRepository repository;
    private PaymentsRepository paymentsRepository;

//    private LiveData<Ownerd> allNotes;
//    private Ownerd getRecord;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new EmployeeRepository(application);
        paymentsRepository = new PaymentsRepository(application);

//        allNotes = repository.getAllNotes();

    }
//    public void insert(Ownerd note) {
//        repository.insert(note);
//    }
//    public void update(Ownerd note) {
//        repository.update(note);
//    }
//    public void delete(Ownerd note) {
//        repository.delete(note);
//    }
//
//    public LiveData<Ownerd> getAllNotes() {
//        return allNotes;
//    }

    public Boolean getDate(String date) {
        return repository.getDate(date);
    }
    public void insert(Date1 date1) {
        repository.insertDate(date1);
    }
    public List<empdata> getAllEmpData() {
        return repository.getAllEmpData();
    }
    public void insertAttendance(List<Attendance> attendanceList) {
        repository.insertAttendance(attendanceList);
    }
    public void insertEmployee(empdata empdata1) {
        repository.insertEmployee(empdata1);
    }
    public void insertPayment(Payments payments) {
paymentsRepository.insertPayment(payments);
    }
    public List<Attendance> getAttendanceList(String date) {
        return repository.getAttendanceList(date);
    }
    public void updateAttendance(String attend,String date,String mobno) {
        repository.updateAttendance(attend,date,mobno);
    }
    public Boolean empExist(String mobno) {
      return  repository.empExist(mobno);
    }
    public empdata getEmpData(String mobno) {
      return   repository.getEmpData(mobno);
    }
    public Integer getPresentCount(String monthYear,String mobno) {
    return  repository.getPresentCount(monthYear,mobno);
    }
    public Integer getHalfdayCount(String monthYear,String mobno) {
        return  repository.getHalfdayCount(monthYear,mobno);
    }
    public Boolean getAttendanceExist(String date) {
        return repository.getAttendanceExist(date);
    }
    public  List<Payments> getMonthPaymentsList(String monthYear,String mobno)
    {
        return paymentsRepository.getMonthPaymentsList(monthYear,mobno);
    }
    public  Float getMonthTotalPayments(String monthYear,String mobno) {
        return paymentsRepository.getMonthTotalPayments(monthYear,mobno);

    }
    public void insertOrUpdateEmpMonthlyClosBal(EmpMonthlyClosBal empMonthlyClosBal) {
        repository.insertOrUpdateEmpMonthlyClosBal(empMonthlyClosBal);
    }
    public void insertOrUpdateEmpTotalBal(EmpTotalBalance empTotalBalance) {
        repository.insertOrUpdateEmpTotalBal(empTotalBalance);
    }
    public EmpMonthlyClosBal getEmpMonthRecord(String mobno, String monthYear) {
   return repository.getEmpMonthRecord(mobno,monthYear);
    }
    public ReportRecordObject getSumEmpMonthRecord(String ownerMobno, String monthYear) {
    return  repository.getSumEmpMonthRecord(ownerMobno,monthYear);
    }
    public void insertOrUpdateOwnerMonthlyClosBal(Report report) {
        repository.insertOrUpdateOwnerMonthlyClosBal(report);
    }
    public List<EmpMonthlyClosBal> getListEmpMonthRecord(String ownerMobno, String monthYear) {
        return repository.getListEmpMonthRecord(ownerMobno,monthYear);
    }
    public Report getOwnerMonthRecord(String ownerMobno, String monthYear) {
        return repository.getOwnerMonthRecord(ownerMobno,monthYear);

    }
    }
