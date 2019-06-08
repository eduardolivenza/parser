package com.edu.parserTest.business;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountServiceImpl implements AccountService {

    private Map<String, Account> accounts;

    public AccountServiceImpl(){
        this.accounts = new HashMap<String, Account>();
        fillDummyData();
    }


    public Double checkBalance( String accountIdentifier) {
        return this.accounts.get(accountIdentifier).getBalance();
    }

    public boolean withdrawAmount(String accountIdentifier, Integer quantity) {

        Account account = this.accounts.get(accountIdentifier);
        Double remaining = account.getBalance() - quantity;
        if ( remaining> 0) {
            account.setBalance(remaining);
            return true;
        }
        return false;
    }

    private void fillDummyData(){
        this.accounts.put("01001", new Account("01001").setBalance(2738.59));
        this.accounts.put("01002", new Account("01002").setBalance(23.00));
        this.accounts.put("01003", new Account("01003").setBalance(0.00));
    }
}
