package com.edu.parserTest.business;

public class NotAvailableAmountException extends Exception {
    public NotAvailableAmountException(){
        super("Requested amount of money is not available in this account");
    }
}
