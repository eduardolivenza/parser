package com.edu.parserTest.business;

public class Account {

    private String accountId;

    private Double balance;

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public Account setBalance(Double balance) {
        this.balance = balance;
        return this;
    }
}
