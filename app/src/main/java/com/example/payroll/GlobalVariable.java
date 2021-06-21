package com.example.payroll;

import com.example.payroll.util.DataBindersValues;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GlobalVariable {
    public static String mob_no;
    public static String Owner_nm;
    public static String emp_mob_no;

    public static String dateToString(LocalDate date1, String m) {

        return date1.format(DateTimeFormatter.ofPattern(m));
    }

    public static String numToCurr(double number) {
        String COUNTRY = "IN";
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(number);
    }

    public static DataBindersValues monthYearConv( LocalDate monthYear) {

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M-yyyy");
       // LocalDate y = LocalDate.parse(monthYear, formatter1);
        String month = GlobalVariable.dateToString(monthYear, "MMM");
        String mY=GlobalVariable.dateToString(monthYear, "MMM yyyy");
        monthYear = monthYear.minusMonths(1);
        String lastMonth = GlobalVariable.dateToString(monthYear, "MMM");
return new DataBindersValues(month,lastMonth,mY);

    }
}