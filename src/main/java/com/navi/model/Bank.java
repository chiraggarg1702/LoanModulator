package com.navi.model;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private String bankName;
    private Map<String, Account> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts =  new HashMap<>();
    }

    public Account getAccount(String accountName) {
        return accounts.get(accountName);
    }

    public void setAccount(Account account) {
        this.accounts.put(account.getAccountName(),account);
    }
}
