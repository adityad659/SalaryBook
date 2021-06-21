package com.example.payroll.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.payroll.Database.EmployeeDatabase;
import com.example.payroll.GlobalVariable;
import com.example.payroll.data.Attendance;
import com.example.payroll.data.AttendanceDao;
import com.example.payroll.data.Date1;
import com.example.payroll.data.DateDao;
import com.example.payroll.data.EmpDetailsDao;
import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.data.EmpMonthlyClosBalDao;
import com.example.payroll.data.EmpTotalBalance;
import com.example.payroll.data.EmpTotalBalanceDao;
import com.example.payroll.data.Report;
import com.example.payroll.data.ReportDao;
import com.example.payroll.data.ReportRecordObject;
import com.example.payroll.data.empdata;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmployeeRepository {
    //empDetails table
    private EmpDetailsDao empDetailsDao;
    private List<empdata> empdata;
    //Date table
    private DateDao dateDao;
    private Date1 date1;
    //Attendance table
    private AttendanceDao attendanceDao;
    private LiveData<List<Attendance>> atttendanceListLiveData;
    //EmpMonthlyClosBal table
    private EmpMonthlyClosBalDao empMonthlyClosBalDao;
  //  private LiveData<List<Attendance>> atttendanceListLiveData;
//EmpTotalBalanceDao table
  private EmpTotalBalanceDao empTotalBalanceDao;
    //EmpTotalBalanceDao table
    private ReportDao reportDao;


    public EmployeeRepository(Application application) {
        EmployeeDatabase database = EmployeeDatabase.getInstance(application);
        empDetailsDao = database.empDetailsDao();
        dateDao = database.dateDao();
        attendanceDao = database.attendanceDao();
        empMonthlyClosBalDao=database.empMonthlyClosBalDao();
        empTotalBalanceDao=database.empTotalBalanceDao();
        reportDao=database.reportDao();
//        empdataLiveData = empDetailsDao.getEmp(GlobalVariable.emp_mob_no);
        //        atttendanceListLiveData = attendanceDao.getAttendance(GlobalVariable.mob_no, date);

    }
    //-----------------get owner month record

    public Report getOwnerMonthRecord(String ownerMobno, String monthYear) {
        AsyncTask<Void, Void, Report >r= new getOwnerMonthRecordAsyncTask(reportDao,ownerMobno, monthYear).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getOwnerMonthRecordAsyncTask extends AsyncTask<Void, Void,  Report> {
        private ReportDao reportDao;
        String ownerMobno, monthYear;

        private getOwnerMonthRecordAsyncTask( ReportDao reportDao,String ownerMobno, String monthYear)  {
            this.reportDao = reportDao;
            this.ownerMobno=ownerMobno;
            this.monthYear=monthYear;
        }
        @Override
        protected Report doInBackground(Void... voids) {

            return reportDao.getOwnerMonthRecord(ownerMobno,monthYear);
        }
    }

    //-----------------get summation monthly closing balance of all employee

    public ReportRecordObject getSumEmpMonthRecord(String ownerMobno, String monthYear) {
        AsyncTask<Void, Void, ReportRecordObject> r= new getSumEmpMonthRecordAsyncTask(empMonthlyClosBalDao,ownerMobno, monthYear).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getSumEmpMonthRecordAsyncTask extends AsyncTask<Void, Void, ReportRecordObject> {
        private EmpMonthlyClosBalDao empMonthlyClosBalDao;
        String ownerMobno, monthYear;
        private getSumEmpMonthRecordAsyncTask( EmpMonthlyClosBalDao empMonthlyClosBalDao,String ownerMobno, String monthYear)  {
            this.empMonthlyClosBalDao = empMonthlyClosBalDao;
            this.ownerMobno=ownerMobno;
            this.monthYear=monthYear;
        }
        @Override
        protected ReportRecordObject doInBackground(Void... voids) {

            return empMonthlyClosBalDao.getMonthRecordSum(ownerMobno,monthYear);
        }
    }
    //-----------------get List of employee monthly closing balance for specific owner

    public List<EmpMonthlyClosBal> getListEmpMonthRecord(String ownerMobno, String monthYear) {
        AsyncTask<Void, Void, List<EmpMonthlyClosBal> >r= new getListEmpMonthRecordAsyncTask(empMonthlyClosBalDao,ownerMobno, monthYear).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getListEmpMonthRecordAsyncTask extends AsyncTask<Void, Void,  List<EmpMonthlyClosBal>> {
        private EmpMonthlyClosBalDao empMonthlyClosBalDao;
        String ownerMobno, monthYear;

        private getListEmpMonthRecordAsyncTask( EmpMonthlyClosBalDao empMonthlyClosBalDao,String ownerMobno, String monthYear)  {
            this.empMonthlyClosBalDao = empMonthlyClosBalDao;
            this.ownerMobno=ownerMobno;
            this.monthYear=monthYear;
        }
        @Override
        protected List<EmpMonthlyClosBal> doInBackground(Void... voids) {

            return empMonthlyClosBalDao.getListEmpMonthRecord(ownerMobno,monthYear);
        }
    }

    //-----------------insert or update Owner monthly closing balance

    public void insertOrUpdateOwnerMonthlyClosBal(Report report) {
        new insertOrUpdateOwnerMonthlyClosBalAsyncTask(reportDao,report).execute();

        return ;
    }
    private static class insertOrUpdateOwnerMonthlyClosBalAsyncTask extends AsyncTask<Void, Void,  Void> {
        private ReportDao reportDao;
        Report report;

        private insertOrUpdateOwnerMonthlyClosBalAsyncTask( ReportDao reportDao,Report report)  {
            this.reportDao = reportDao;
            this.report=report;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            if (reportDao.isRecordExist(report.getOwnerMobno(), report.getMonthYear()) == true) {
                reportDao.delete(report.getOwnerMobno(), report.getMonthYear());
                reportDao.insert(report);
            } else {
                reportDao.insert(report);
            }
            return null;
        }
    }

    //-----------------get employee monthly closing balance

    public EmpMonthlyClosBal getEmpMonthRecord(String mobno, String monthYear) {
        AsyncTask<Void, Void, EmpMonthlyClosBal> r= new getEmpMonthRecordAsyncTask(empMonthlyClosBalDao,mobno, monthYear).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getEmpMonthRecordAsyncTask extends AsyncTask<Void, Void,  EmpMonthlyClosBal> {
        private EmpMonthlyClosBalDao empMonthlyClosBalDao;
        String mobno, monthYear;

        private getEmpMonthRecordAsyncTask( EmpMonthlyClosBalDao empMonthlyClosBalDao,String mobno, String monthYear)  {
            this.empMonthlyClosBalDao = empMonthlyClosBalDao;
            this.mobno=mobno;
            this.monthYear=monthYear;

        }


        @Override
        protected EmpMonthlyClosBal doInBackground(Void... voids) {

            return empMonthlyClosBalDao.getEmpMonthRecord(mobno,monthYear);
        }
    }


    //-----------------insert or update employee total balance

    public void insertOrUpdateEmpTotalBal(EmpTotalBalance empTotalBalance) {
        new insertOrUpdateEmpTotalBalAsyncTask(empTotalBalanceDao).execute(empTotalBalance);

        return ;
    }
    private static class insertOrUpdateEmpTotalBalAsyncTask extends AsyncTask<EmpTotalBalance, Void,  Void> {
        private EmpTotalBalanceDao empTotalBalanceDao;


        private insertOrUpdateEmpTotalBalAsyncTask(EmpTotalBalanceDao empTotalBalanceDao)  {
            this.empTotalBalanceDao = empTotalBalanceDao;
        }

        @Override
        protected Void doInBackground(EmpTotalBalance... empTotalBalances) {
            empTotalBalanceDao.insert(empTotalBalances[0]);
            return null;
        }
    }

    //-----------------insert or update employee monthly closing balance

   public void insertOrUpdateEmpMonthlyClosBal(EmpMonthlyClosBal empMonthlyClosBal) {
        new insertOrUpdateEmpMonthlyClosBalAsyncTask(empMonthlyClosBalDao,empMonthlyClosBal).execute();

        return ;
    }
    private static class insertOrUpdateEmpMonthlyClosBalAsyncTask extends AsyncTask<Void, Void,  Void> {
        private EmpMonthlyClosBalDao empMonthlyClosBalDao;
        EmpMonthlyClosBal empMonthlyClosBal;

        private insertOrUpdateEmpMonthlyClosBalAsyncTask( EmpMonthlyClosBalDao empMonthlyClosBalDao,EmpMonthlyClosBal empMonthlyClosBal)  {
            this.empMonthlyClosBalDao = empMonthlyClosBalDao;
            this.empMonthlyClosBal=empMonthlyClosBal;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            if (empMonthlyClosBalDao.isRecordExist(empMonthlyClosBal.getMobno(), empMonthlyClosBal.getMonthYear()) == true) {
                empMonthlyClosBalDao.delete(empMonthlyClosBal.getMobno(), empMonthlyClosBal.getMonthYear());
                empMonthlyClosBalDao.insert(empMonthlyClosBal);
            } else {
                empMonthlyClosBalDao.insert(empMonthlyClosBal);
            }
            return null;
        }
    }

    //-----------------get Halfday count

    public Integer getHalfdayCount(String monthYear,String mobno) {
        AsyncTask<Void, Void, Integer> r = new getHalfdayCountAsyncTask(attendanceDao,monthYear,mobno).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getHalfdayCountAsyncTask extends AsyncTask<Void, Void,  Integer> {
        private AttendanceDao attendanceDao;
        String monthYear, mobno;

        private getHalfdayCountAsyncTask(AttendanceDao attendanceDao, String monthYear, String mobno) {
            this.attendanceDao = attendanceDao;
            this.monthYear = monthYear;
            this.mobno = mobno;
        }



        @Override
        protected Integer doInBackground(Void... voids) {
            return attendanceDao.getMonthHalfDay(monthYear,mobno);
        }
    }

    //-----------------get present count

    public Integer getPresentCount(String monthYear,String mobno) {
        AsyncTask<Void, Void, Integer> r = new getPresentCountAsyncTask(attendanceDao,monthYear,mobno).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getPresentCountAsyncTask extends AsyncTask<Void, Void,  Integer> {
        private AttendanceDao attendanceDao;
        String monthYear,mobno;

        private getPresentCountAsyncTask( AttendanceDao attendanceDao,String monthYear,String mobno) {
            this.attendanceDao = attendanceDao;
            this.monthYear=monthYear;
            this.mobno =mobno;
        }


        @Override
        protected Integer doInBackground(Void... voids) {
            return attendanceDao.getMonthPresents(monthYear,mobno);
        }
    }

    //-----------------update attendance

    public void updateAttendance(String attend,String date,String mobno) {
        AsyncTask<Void, Void, Void> r = new updateAttendanceAsyncTask(attendanceDao,attend,date,mobno).execute();

        return ;
    }
    private static class updateAttendanceAsyncTask extends AsyncTask<Void, Void,  Void> {
        private AttendanceDao attendanceDao;
       private  String attend,date,mobno;
        private updateAttendanceAsyncTask( AttendanceDao attendanceDao,String attend,String date,String mobno) {
            this.attendanceDao = attendanceDao;
            this.attend=attend;
            this.date=date;
            this.mobno=mobno;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            attendanceDao.update(attend,mobno,date);
            return null;
        }
    }

    //-----------------get attendance list

    public List<Attendance> getAttendanceList(String date) {
        AsyncTask<String, Void,  List<Attendance>> r = new getAttendanceListAsyncTask(attendanceDao).execute(date);
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getAttendanceListAsyncTask extends AsyncTask<String, Void,  List<Attendance>> {
        private AttendanceDao attendanceDao;

        private getAttendanceListAsyncTask( AttendanceDao attendanceDao) {
            this.attendanceDao = attendanceDao;
        }


        @Override
        protected List<Attendance> doInBackground(String... strings) {
            return attendanceDao.getAttendance(GlobalVariable.mob_no,strings[0]);
        }
    }

    //-----------------get attendance exist

    public Boolean getAttendanceExist(String date) {
        AsyncTask<String, Void,  Boolean> r = new getAttendanceExistAsyncTask(attendanceDao).execute(date);
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getAttendanceExistAsyncTask extends AsyncTask<String, Void,  Boolean> {
        private AttendanceDao attendanceDao;

        private getAttendanceExistAsyncTask( AttendanceDao attendanceDao) {
            this.attendanceDao = attendanceDao;
        }


        @Override
        protected Boolean doInBackground(String... strings) {
            return attendanceDao.attendanceExist(GlobalVariable.mob_no,strings[0]);
        }
    }

    //-----------------insert attendance

    public void insertAttendance(List<Attendance> attendanceList) {
        new InsertAttendanceAsyncTask(attendanceDao).execute(attendanceList);
    }
    private static class InsertAttendanceAsyncTask extends AsyncTask<List<Attendance>, Void, Void> {
        private AttendanceDao attendanceDao;
        private InsertAttendanceAsyncTask(AttendanceDao attendanceDao) {
            this.attendanceDao = attendanceDao;
        }
        @Override
        protected Void doInBackground(List<Attendance>... lists) {
            attendanceDao.insert(lists[0]);
            return null;
        }
    }

     //--------------------get date

    public Boolean getDate(String date) {
        AsyncTask<String, Void, Boolean> r = new GetDateAsyncTask(dateDao).execute(date);
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class GetDateAsyncTask extends AsyncTask<String, Void, Boolean> {
        private DateDao dateDao;

        private GetDateAsyncTask(DateDao dateDao) {
            this.dateDao = dateDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return dateDao.getDate(strings[0]);
        }
    }

    //--------------------insert date

    public void insertDate(Date1 date1) {
        new InsertDateAsyncTask(dateDao).execute(date1);
    }
    private static class InsertDateAsyncTask extends AsyncTask<Date1, Void, Void> {
        private DateDao dateDao;

        private InsertDateAsyncTask(DateDao dateDao) {

            this.dateDao = dateDao;
        }

        @Override
        protected Void doInBackground(Date1... date1s) {
            dateDao.insert(date1s[0]);
            return null;
        }
    }

    //--------------------employee exist

    public Boolean empExist(String mobno) {
    AsyncTask<String, Void, Boolean> r = new EmpExistAsyncTask(empDetailsDao).execute(mobno);
    try {
        return r.get();
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return null;
}
    private static class EmpExistAsyncTask extends AsyncTask<String, Void, Boolean> {
        private EmpDetailsDao empDetailsDao;

        private EmpExistAsyncTask(EmpDetailsDao empDetailsDao) {
            this.empDetailsDao = empDetailsDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return empDetailsDao.empExist(strings[0]);
        }
    }

    //--------------------get  employee data

    public empdata getEmpData(String mobno) {
        AsyncTask<String, Void,  empdata> r = new getEmpDataAsyncTask(empDetailsDao).execute(mobno);
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getEmpDataAsyncTask extends AsyncTask<String, Void,  empdata> {
        private EmpDetailsDao empDetailsDao;

        private getEmpDataAsyncTask(EmpDetailsDao empDetailsDao) {
            this.empDetailsDao = empDetailsDao;
        }


        @Override
        protected com.example.payroll.data.empdata doInBackground(String... strings) {
            return empDetailsDao.getEmp1(strings[0]);
        }
    }

    //--------------------get all employee data

    public List<empdata> getAllEmpData() {
        AsyncTask<Void, Void,  List<empdata>> r = new getAllEmpDataAsyncTask(empDetailsDao).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getAllEmpDataAsyncTask extends AsyncTask<Void, Void,  List<empdata>> {
        private EmpDetailsDao empDetailsDao;

        private getAllEmpDataAsyncTask(EmpDetailsDao empDetailsDao) {
            this.empDetailsDao = empDetailsDao;
        }

        @Override
        protected List<empdata> doInBackground(Void... voids) {
            return  empDetailsDao.getAllEmp(GlobalVariable.mob_no);
        }
    }

    //-----------------insert employee

    public void insertEmployee(empdata empdata1) {
        new InsertEmployeeAsyncTask(empDetailsDao).execute(empdata1);
    }
    private static class InsertEmployeeAsyncTask extends AsyncTask<empdata, Void, Void> {
        private EmpDetailsDao empDetailsDao;
        private InsertEmployeeAsyncTask(EmpDetailsDao empDetailsDao) {
            this.empDetailsDao = empDetailsDao;
        }

        @Override
        protected Void doInBackground(com.example.payroll.data.empdata... empdata) {
            empDetailsDao.insert(empdata[0]);
            return null;
        }
    }


}