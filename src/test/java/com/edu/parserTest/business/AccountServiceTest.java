package com.edu.parserTest.business;

import com.edu.parserTest.business.AccountService;
import com.edu.parserTest.business.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    public void setUp(){
        accountService = new AccountServiceImpl();
    }
    @Test
    public void check_initial_balances_test() {
        assertEquals((double)(accountService.checkBalance("01001")), 2738.59, "Initial amount for account 1 is the expected");
        assertEquals((double)(accountService.checkBalance("01002")), 23.00, "Initial amount for account 1 is the expected");
        assertEquals((double)(accountService.checkBalance("01003")), 0.0, "Initial amount for account 1 is the expected");
    }

    @Test
    public void check_withdraw_accepted() {
        assertEquals(true, accountService.withdrawAmount("01001", 10),  " Withdrawal of 10gbp was accepted");
        assertEquals((double)(accountService.checkBalance("01001")), 2738.59 - 10, " Amount of the account was reduced 10GBP");
    }

    @Test
    public void check_withdraw_rejected() {
        assertEquals(false, accountService.withdrawAmount("01002", 30),  " Withdrawal of 10gbp was rejected");
        assertEquals((double)(accountService.checkBalance("01002")), 23, " Amount of the account was not modified");
    }

}
