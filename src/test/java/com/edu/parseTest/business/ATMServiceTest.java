package com.edu.parseTest.business;

import com.edu.parserTest.business.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;


public class ATMServiceTest {

    private ATMNotesController atmNotesController;
    private ATMService atmService;

    @BeforeEach
    public void setUp(){
        AccountService mockAccountService = Mockito.spy(new AccountServiceImpl());
        Mockito.doReturn(true).when(mockAccountService).withdrawAmount(any(String.class), any(Integer.class));
        atmNotesController = new ATMNotesController(10,10,10,10);
        atmService = new ATMServiceImpl(mockAccountService, atmNotesController);
    }

    @Test
    public void check_initial_balances_test() throws Exception {
        CurrencyNotesModel notes = atmService.withdrawal("01001", 100);
        assertEquals(2, notes.getNotesOf50(), " We return 2 notes of 50 when someone retrieves 100GBP");
        notes = atmService.withdrawal("01001", 225);
        assertEquals(4, notes.getNotesOf50(),  " We return 2 notes of 50 when someone retrieves 225GBP");
        assertEquals(1, notes.getNotesOf20(),  " We return 1 notes of 20 when someone retrieves 225GBP");
        assertEquals(1, notes.getNotesOf5(),  " We return 1 notes of 5 when someone retrieves 225GBP");

    }
}
