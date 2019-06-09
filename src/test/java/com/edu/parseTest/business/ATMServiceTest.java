package com.edu.parseTest.business;

import com.edu.parserTest.business.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;


public class ATMServiceTest {

    private ATMNotesController atmNotesController;
    private ATMService atmService;
    private  AccountService mockAccountService;

    @BeforeEach
    public void setUp(){
        mockAccountService = Mockito.spy(new AccountServiceImpl());
        Mockito.doReturn(true).when(mockAccountService).withdrawAmount(any(String.class), any(Integer.class));
        atmNotesController = Mockito.spy(new ATMNotesController(6,6,6,6));
        atmService = new ATMServiceImpl(mockAccountService, atmNotesController);
    }

    @Test
    public void accepted_withdrawals_pair() throws Exception {
        CurrencyNotesModel notes = atmService.withdrawal("01001", 100);
        assertEquals(1, notes.getNotesOf50(), " We return 1 note of 50 when someone retrieves 100GBP");
        assertEquals(2, notes.getNotesOf20(), " We return 2 notes of 20 when someone retrieves 100GBP");
        assertEquals(2, notes.getNotesOf5(), " We always return at least 1 note of 5 ");
    }

    @Test
    public void accepted_withdrawals_unpair() throws Exception {
        CurrencyNotesModel notes = atmService.withdrawal("01001", 225);
        assertEquals(4, notes.getNotesOf50(),  " We return 2 notes of 50 when someone retrieves 225GBP");
        assertEquals(1, notes.getNotesOf20(),  " We return 1 notes of 20 when someone retrieves 225GBP");
        assertEquals(1, notes.getNotesOf5(),  " We return 1 notes of 5 when someone retrieves 225GBP");
    }

    @Test
    public void accepted_withdrawals_pair_small_amount() throws Exception {
        CurrencyNotesModel notes = atmService.withdrawal("01001", 20);
        assertEquals(1, notes.getNotesOf10(),  " We expected one note of 10 and 2 of 5 when we retrieve 20 GBP");
        assertEquals(0, notes.getNotesOf20(),  " We expected one note of 10 and 2 of 5 when we retrieve 20 GBP");
        assertEquals(2, notes.getNotesOf5(),  "We expected one note of 10 and 2 of 5 when we retrieve 20 GBP");
    }

    @Test
    public void trying_to_withdrawal_non_allowed_amounts(){
        assertThrows(IncorrectAmountException.class, () -> {
            atmService.withdrawal("01001", 300);
        });
        assertThrows(IncorrectAmountException.class, () -> {
            atmService.withdrawal("01001", 19);
        });
    }

    @Test
    public void trying_to_withdrawal_from_no_funds_accounts(){
        Mockito.doReturn(false).when(mockAccountService).withdrawAmount(any(String.class), any(Integer.class));
        assertThrows(NotAvailableAmountException.class, () -> {
            atmService.withdrawal("01003", 40);
        });
    }

    @Test
    public void trying_to_withdrawal_from_no_funds_atm(){
        atmNotesController = new ATMNotesController();
        atmService = new ATMServiceImpl(mockAccountService, atmNotesController);
        assertThrows(ATMHasNoFundsException.class, () -> {
            atmService.withdrawal("01001", 40);
        });
    }


}
