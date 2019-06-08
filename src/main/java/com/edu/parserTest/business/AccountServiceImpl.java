package com.edu.parserTest.business;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {

    private Map<Integer, Account> accounts;

    public AccountServiceImpl(){
        this.accounts = new HashMap<Integer, Account>();
        fillDummyData();
    }

    public Double checkBalance(Integer accountIdentifier) {
        return this.accounts.get(accountIdentifier).getBalance();
    }

    public boolean withdrawAmount(Integer accountIdentifier, Integer quantity) {
        Account account = this.accounts.get(accountIdentifier);
        Double remaining = account.getBalance() - quantity;
        if ( remaining> 0) {
            account.setBalance(remaining);
            return true;
        }
        return false;
    }

    private void fillDummyData(){
        this.accounts.put(01001, new Account(01001).setBalance(2738.59));
        this.accounts.put(01001, new Account(01002).setBalance(23.00));
        this.accounts.put(01001, new Account(01003).setBalance(0.00));
    }
}
