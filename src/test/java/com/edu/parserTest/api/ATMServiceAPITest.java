package com.edu.parserTest.api;

import com.edu.parserTest.business.atmService.ATMNotesController;
import com.edu.parserTest.business.atmService.ATMService;
import com.edu.parserTest.business.atmService.ATMServiceImpl;
import com.edu.parserTest.business.accountService.AccountService;
import com.edu.parserTest.business.accountService.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMServiceAPITest {


    private ATMService atm;
    private ModelMapper mapper;
    private ATMServiceAPI atmService;

    @BeforeEach
    public void setUp(){
        this.mapper = new ModelMapper();
        AccountService accountService = new AccountServiceImpl();
        ATMNotesController currencyNotesModel = new ATMNotesController();
        this.atm = new ATMServiceImpl(accountService, currencyNotesModel);
        this.atmService = new ATMServiceAPI(atm, mapper);
    }

    private boolean checkNotes(CurrencyNotesVM vm, int notesOf5, int notesOf10, int notesOf20, int notesOf50){
        return ((vm.getNotesOf50()==notesOf50)&&(vm.getNotesOf20()==notesOf20)&&(vm.getNotesOf10()==notesOf10)&&(vm.getNotesOf5()==notesOf5));
    }

    @Test
    public void withdrawal_before_replenish() {
        CurrencyNotesVM returnedNotes = atmService.withdrawal("01001", 55);
        assertTrue(checkNotes(returnedNotes, 0,0,0,0));
    }

    @Test
    public void withdrawal_after_replenish() {
        CurrencyNotesVM insertedMoney = new CurrencyNotesVM(1,1,1,1);
        atmService.replenish(insertedMoney);
        CurrencyNotesVM returnedNotes = atmService.withdrawal("01001", 55);
        assertTrue(checkNotes(returnedNotes, 1,0,0,1));
    }

    @Test
    public void check_balance_after_withdrawal_after_replenish() {
        String accountId = "01001";
        Double value = 2738.59;
        atmService.replenish(new CurrencyNotesVM(10,10,10,10));
        assertEquals(" ACCOUNT: " + accountId + " --> " + String.valueOf(value) + "GBP", atmService.checkBalance(accountId));
        CurrencyNotesVM returnedNotes = atmService.withdrawal(accountId, 155);
        assertEquals(" ACCOUNT: " + accountId + " --> " + String.valueOf(value -155) + "GBP", atmService.checkBalance(accountId));
    }

    @Test
    public void check_no_more_withdrawal_possible_after_withdrawal() {
        String accountId = "01002";
        Double value = 23.0;
        atmService.replenish(new CurrencyNotesVM(10,10,10,10));
        CurrencyNotesVM returnedNotes = atmService.withdrawal(accountId, 20);
        assertTrue(checkNotes(returnedNotes, 2,1,0,0));
        assertEquals(" ACCOUNT: " + accountId + " --> " + String.valueOf(value -20) + "GBP", atmService.checkBalance(accountId), "First retrieve was succesful");
        returnedNotes = atmService.withdrawal(accountId, 20);
        assertEquals(" ACCOUNT: " + accountId + " --> " + String.valueOf(value -20) + "GBP", atmService.checkBalance(accountId), "Account balance was not modified after second attempt");
        assertTrue(checkNotes(returnedNotes, 0,0,0,0));
    }
}
