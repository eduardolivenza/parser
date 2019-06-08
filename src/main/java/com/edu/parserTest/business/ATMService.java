package com.edu.parserTest.business;

public interface ATMService {

    void replenish( CurrencyNotesModel newNotes);

    CurrencyNotesModel withdrawal( String accountId,  Integer quantity) throws Exception;

    String checkBalance( String accountId);
}
