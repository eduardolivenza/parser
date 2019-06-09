package com.edu.parserTest.api;

import com.edu.parserTest.business.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;

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
}
