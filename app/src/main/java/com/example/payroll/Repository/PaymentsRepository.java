package com.example.payroll.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.payroll.Database.EmployeeDatabase;
import com.example.payroll.data.Payments;
import com.example.payroll.data.PaymentsDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PaymentsRepository {
    //Payments table
    private PaymentsDao paymentsDao;
    private LiveData<List<Payments>> paymentsListLiveData;

    public PaymentsRepository(Application application) {
        EmployeeDatabase database = EmployeeDatabase.getInstance(application);
        paymentsDao = database.paymentsDao();
//        paymentsListLiveData = paymentsDao.getMonthPayments(monthYear,mobno);

    }
    //-----------------insert payments

    public void insertPayment(Payments payments) {
        new InsertPaymentAsyncTask(paymentsDao).execute(payments);
    }
    private static class InsertPaymentAsyncTask extends AsyncTask<Payments, Void, Void> {
        private PaymentsDao paymentsDao;
        private InsertPaymentAsyncTask(PaymentsDao paymentsDao) {
            this.paymentsDao = paymentsDao;
        }


        @Override
        protected Void doInBackground(Payments... payments) {
            paymentsDao.insert(payments[0]);
            return null;
        }
    }
    //-----------------get payments list for particular month and employee

    public  List<Payments> getMonthPaymentsList(String monthYear,String mobno) {
        AsyncTask<Void, Void, List<Payments>> r=new getMonthPaymentsListAsyncTask(paymentsDao,monthYear,mobno).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getMonthPaymentsListAsyncTask extends AsyncTask<Void, Void, List<Payments>> {
        private PaymentsDao paymentsDao;
        String monthYear,mobno;
        private getMonthPaymentsListAsyncTask(PaymentsDao paymentsDao,String monthYear,String mobno) {
            this.paymentsDao = paymentsDao;
            this.monthYear=monthYear;
            this.mobno=mobno;
        }


        @Override
        protected List<Payments> doInBackground(Void... voids) {

            return paymentsDao.getMonthPayments(monthYear,mobno);
        }
    }
    //-----------------get total payment for particular month and employee

    public  Float getMonthTotalPayments(String monthYear,String mobno) {
        AsyncTask<Void, Void, Float> r=new getMonthTotalPaymentsAsyncTask(paymentsDao,monthYear,mobno).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getMonthTotalPaymentsAsyncTask extends AsyncTask<Void, Void, Float> {
        private PaymentsDao paymentsDao;
        String monthYear,mobno;
        private getMonthTotalPaymentsAsyncTask(PaymentsDao paymentsDao,String monthYear,String mobno) {
            this.paymentsDao = paymentsDao;
            this.monthYear=monthYear;
            this.mobno=mobno;
        }


        @Override
        protected Float doInBackground(Void... voids) {

            return  paymentsDao.getMonthTotalPayments(monthYear,mobno);

        }
    }


}
