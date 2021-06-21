package com.example.payroll.ProfileDetails;

public class dataClosingBalance {
    String date;
    String mobno;

    String date1;
    Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public dataClosingBalance(String date, String mobno, String date1, Integer amount) {
        this.date = date;
        this.mobno = mobno;
        this.date1=date1;
        this.amount=amount;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }
}
