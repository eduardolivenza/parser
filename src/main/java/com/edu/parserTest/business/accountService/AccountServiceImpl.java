package com.edu.parserTest.business.accountService;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountServiceImpl implements AccountService {

    private Map<String, Account> accounts;

    public AccountServiceImpl(){
        this.accounts = new HashMap<String, Account>();
        fillDummyData();
    }


    public Double checkBalance( String accountIdentifier) throws NotExistingAccountException {
        Account account = checkAccountExists(accountIdentifier);
        return account.getBalance();
    }

    private Account checkAccountExists(String accountIdentifier) throws NotExistingAccountException {
        if (this.accounts.containsKey(accountIdentifier)){
            return this.accounts.get(accountIdentifier);
        }else{
            throw new NotExistingAccountException(accountIdentifier);
        }
    }

    public boolean withdrawAmount(String accountIdentifier, Integer quantity) throws NotExistingAccountException {

        Account account = checkAccountExists(accountIdentifier);
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
