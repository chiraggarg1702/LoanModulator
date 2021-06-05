package com.navi.model;

import java.util.Map;
import java.util.TreeMap;

public class Account {
    private String bankName;
    private String accountName;
    private int principal;
    private int rate;
    private int number_of_years;

    //derived fields
    private int interest;
    private int amount;
    private int number_of_emi;
    private int emiAmount;
    private int currentEmi;
    private Map<Integer,Integer> lumpSumSchedule = new TreeMap<>();


    public Account(String bankName, String accountName, int principal, int rate, int number_of_years) {
        this.bankName = bankName;
        this.accountName = accountName;
        this.principal = principal;
        this.rate = rate;
        this.number_of_years = number_of_years;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumber_of_emi() {
        return number_of_emi;
    }

    public void setNumber_of_emi(int number_of_emi) {
        this.number_of_emi = number_of_emi;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getPrincipal() {
        return principal;
    }

    public int getRate() {
        return rate;
    }

    public int getNumber_of_years() {
        return number_of_years;
    }

    public int getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(int emiAmount) {
        this.emiAmount = emiAmount;
    }

    public int getCurrentEmi() {
        return currentEmi;
    }

    public void setCurrentEmi(int currentEmi) {
        this.currentEmi = currentEmi;
    }

    public Map<Integer, Integer> getLumpSumSchedule() {
        return lumpSumSchedule;
    }

    public void setLumpSumSchedule(int key,int value) {
        if (lumpSumSchedule.containsKey(key)){
            value += lumpSumSchedule.get(key);
        }
        this.lumpSumSchedule.put(key,value);
    }

}
