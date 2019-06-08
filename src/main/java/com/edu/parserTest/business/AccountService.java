package com.edu.parserTest.business;

public interface AccountService {

    Double checkBalance(String accountIdentifier);

    boolean withdrawAmount(String accountIdentifier, Integer quantity);
}
