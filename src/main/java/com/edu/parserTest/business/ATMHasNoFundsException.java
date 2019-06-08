package com.edu.parserTest.business;

public class ATMHasNoFundsException extends Exception {
    public  ATMHasNoFundsException(){
        super("This ATM doesn't has enough notes to attend your request");
    }
}
