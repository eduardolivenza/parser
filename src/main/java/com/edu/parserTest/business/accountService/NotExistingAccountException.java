package com.edu.parserTest.business.accountService;

public class NotExistingAccountException extends Exception {
    public NotExistingAccountException(String accountIdentifier) {
        super("Account " + accountIdentifier + " doesn't exist");
    }
}
