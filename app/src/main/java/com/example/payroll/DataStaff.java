package com.example.payroll;

public class DataStaff {
    String name;
    String attend;
    String mobno;
    String balance;
    Float amount;



    public DataStaff(String name, String attend, String mobno, String balance, Float amount) {
        this.name = name;
        this.attend = attend;
        this.mobno = mobno;
        this.balance = balance;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
