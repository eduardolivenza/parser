package com.edu.parserTest.business.atmService;

import com.edu.parserTest.business.accountService.AccountService;
import com.edu.parserTest.business.accountService.NotExistingAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        Double value = null;
        try {
            value = accountService.checkBalance(accountId);
            return " ACCOUNT: " + accountId + " --> " + value + "GBP";
        } catch (NotExistingAccountException e) {
            return e.getMessage();
        }
    }

    public CurrencyNotesModel withdrawal( String accountId,  Integer quantity) throws Exception {
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
        CurrencyNotesModel returnedNotes = new CurrencyNotesModel();
        if (checkAtmAvailability(ATMNotes.NoteOf5, 2)) {
            if (quantity % 10 == 0){
                returnedNotes.setNotesOf5(2);
            }
            else {
                returnedNotes.setNotesOf5(1);
            }
            remains = quantity - (returnedNotes.getNotesOf5() * 5);
        }
        remains = checkNotes(ATMNotes.NoteOf50, 50, returnedNotes, remains);
        remains = checkNotes(ATMNotes.NoteOf20, 20, returnedNotes, remains);
        remains = checkNotes(ATMNotes.NoteOf10, 10, returnedNotes, remains);
        remains = checkNotes(ATMNotes.NoteOf5, 5, returnedNotes, remains);
        if (remains >0) {
            throw new ATMHasNoFundsException();
        }
        else{
                updateAtmMoney(returnedNotes);
        }
        return returnedNotes;
    }

    private Integer checkNotes(ATMNotes note, int noteValue, CurrencyNotesModel notesModel, Integer remains){
         if (remains >= noteValue) {
             int notesAmount = remains / noteValue;
             if (checkAtmAvailability(note, notesAmount)) {
                 remains = remains % noteValue;
                 notesModel.setNote(note, notesAmount);
             }
         }
         return remains;
    }

    private void updateAtmMoney(CurrencyNotesModel model){
        atmNotesController.setNotesOf5(atmNotesController.getNotesOf5()- model.getNotesOf5());
        atmNotesController.setNotesOf10(atmNotesController.getNotesOf10()-model.getNotesOf10());
        atmNotesController.setNotesOf20(atmNotesController.getNotesOf20()-model.getNotesOf20());
        atmNotesController.setNotesOf50(atmNotesController.getNotesOf50()-model.getNotesOf50());
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
