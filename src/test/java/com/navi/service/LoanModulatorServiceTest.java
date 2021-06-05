package com.navi.service;

import com.navi.exceptions.DuplicateAccountCreationException;
import com.navi.model.Bank;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LoanModulatorServiceTest extends TestCase {

    @Test(expected = DuplicateAccountCreationException.class)
    public void testCreateAccount() throws Exception {
        final LoanModulatorService loanModulatorService = new LoanModulatorService();
        String bankName = "IDIDI",accountName = "Dale";
        int principal = 10000;
        int years = 2;
        int rate = 2;
        loanModulatorService.createAccount(bankName,accountName,principal,rate,years);
    }
}