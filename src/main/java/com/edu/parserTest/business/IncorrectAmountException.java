package com.edu.parserTest.business;

public class IncorrectAmountException extends Exception {
    public IncorrectAmountException(){
        super("Requested money must be a multiple of 5 between 20 and 250");
    }
}
