package com.edu.parserTest.business.atmService;

public class NotAvailableAmountException extends Exception {
    public NotAvailableAmountException(){
        super("Requested amount of money is not available in this account");
    }
}
