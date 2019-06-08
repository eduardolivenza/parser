package com.edu.parserTest.business;

public interface AccountService {

    Double checkBalance(Integer accountIdentifier);

    boolean withdrawAmount(Integer accountIdentifier, Integer quantity);
}
