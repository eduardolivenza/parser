package com.edu.parserTest.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


enum ATMNotes { NoteOf5, NoteOf10, NoteOf20, NoteOf50 };

@Component
public class ATMServiceImpl implements ATMService{

    private AccountService accountService;
    private ATMNotesController atmNotesController;

    @Autowired
    public ATMServiceImpl(AccountService accountService, ATMNotesController currencyNotesModel) {
        this.accountService = accountService;
        this.atmNotesController = currencyNotesModel;
    }

    public void replenish( CurrencyNotesModel newNotes)
    {
        this.atmNotesController.setNotesOf5(this.atmNotesController.getNotesOf5() + newNotes.getNotesOf5());
        this.atmNotesController.setNotesOf10(this.atmNotesController.getNotesOf10() + newNotes.getNotesOf10());
        this.atmNotesController.setNotesOf20(this.atmNotesController.getNotesOf20() + newNotes.getNotesOf20());
        this.atmNotesController.setNotesOf50(this.atmNotesController.getNotesOf50() + newNotes.getNotesOf50());
    }

    public String checkBalance( String accountId)
    {
        Double value = accountService.checkBalance(accountId);
        return " ACCOUNT: " + accountId + " --> " + value + "GBP";
    }

    public CurrencyNotesModel withdrawal( String accountId,  Integer quantity) throws IncorrectAmountException, NotAvailableAmountException, ATMHasNoFundsException {
        if ((quantity > 250) || (quantity < 20) ||( quantity % 5 != 0))
        {
            throw new IncorrectAmountException();
        }
        if (this.accountService.withdrawAmount(accountId, quantity)) {
            return calculateBestNotes( quantity);
        }
        else {
            throw new NotAvailableAmountException();
        }
    }

    private CurrencyNotesModel calculateBestNotes(Integer quantity) throws ATMHasNoFundsException
    {
        Integer remains = quantity;
        int notesOf5 = 0, notesOf10 =0, notesOf20=0, notesOf50=0 ;
        if (quantity >= 50) {
            notesOf50 = quantity / 50;
            if (checkAtmAvailability(ATMNotes.NoteOf50, notesOf50)) {
                remains = quantity % 50;
            }
        }
        if (remains >= 20) {
            notesOf20 = remains / 20;
            if (checkAtmAvailability(ATMNotes.NoteOf20, notesOf20)) {
                remains = quantity % 20;
            }
        }
        if (remains >= 10) {
            notesOf10 = remains / 10;
            if (checkAtmAvailability(ATMNotes.NoteOf10, notesOf10)) {
                remains = quantity % 10;
            }
        }
        if (remains >= 5){
            notesOf5 = remains / 5;
            if (checkAtmAvailability(ATMNotes.NoteOf5, notesOf5)) {
                atmNotesController.setNotesOf5(atmNotesController.getNotesOf5()-notesOf5);
                atmNotesController.setNotesOf10(atmNotesController.getNotesOf10()-notesOf10);
                atmNotesController.setNotesOf20(atmNotesController.getNotesOf20()-notesOf20);
                atmNotesController.setNotesOf50(atmNotesController.getNotesOf50()-notesOf50);
            }
            else {
                throw new ATMHasNoFundsException();
            }
        }
        return new CurrencyNotesModel(notesOf5,notesOf10,notesOf20,notesOf50);
    }


    private boolean checkAtmAvailability(ATMNotes note, Integer amount) {
        boolean isAvailable = false;
        switch (note) {
            case NoteOf5:
                if (this.atmNotesController.getNotesOf5()>= amount){ isAvailable = true;}
                break;
            case NoteOf10:
                if (this.atmNotesController.getNotesOf10()>= amount){ isAvailable = true;}
                break;
            case NoteOf20:
                if (this.atmNotesController.getNotesOf20()>= amount){ isAvailable = true;}
                break;
            case NoteOf50:
                if (this.atmNotesController.getNotesOf50()>= amount){ isAvailable = true;}
                break;
        }
        return isAvailable;
    }




}
