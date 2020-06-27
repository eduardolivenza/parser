package com.edu.parserTest.business.accountService;

public interface AccountService {

    Double checkBalance(String accountIdentifier) throws NotExistingAccountException;

    boolean withdrawAmount(String accountIdentifier, Integer quantity) throws NotExistingAccountException;
}
