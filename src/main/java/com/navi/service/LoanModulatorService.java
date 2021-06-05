package com.navi.service;

import com.navi.exceptions.BankAccountMismatchException;
import com.navi.exceptions.CreateAccountException;
import com.navi.exceptions.DuplicateAccountCreationException;
import com.navi.exceptions.InvalidBankException;
import com.navi.model.Account;
import com.navi.model.Bank;

import java.util.*;

/**
 * Service for enable the functioning of a loan modulator. This will have all the business logic of
 * how the loan modulator service will operate.
 */
public class LoanModulatorService {
    private Map<String,Bank> banks = new HashMap<>();

    public void createAccount(String bankName, String accountName, int principal, int rate, int years) throws Exception {
        Bank bank;
        int interest = 0;
        int amount = 0;
        int emiAmount = 0;

        if(!banks.containsKey(bankName)){
            bank = new Bank(bankName);
            banks.put(bankName,bank);
        }else{
            bank = banks.get(bankName);
        }
        if(validateIfAccountExistsInBank(bank, accountName)){
            throw new DuplicateAccountCreationException(accountName+" has already taken a loan from bank "+bankName);
        }

        try{
            interest = (principal*rate*years) / 100;
            amount = principal+interest;
            double emi = amount/(double)(years*12);
            emiAmount = (int)Math.ceil(emi);

            Account account = new Account(bankName,accountName,principal,rate,years);
            account.setInterest(interest);
            account.setAmount(amount);
            account.setNumber_of_emi(account.getNumber_of_years()*12);
            account.setEmiAmount(emiAmount);
            account.setCurrentEmi(0);

            bank.setAccount(account);

        }catch (Exception ex){
            throw new CreateAccountException(ex.getMessage());
        }

    }

    public void doPayment(String bankName, String accountName, int paymentAmount, int emiNumber) throws Exception{
        Bank bank = banks.get(bankName);
        if(bank == null){
            throw new InvalidBankException(bankName+" either is not a bank or has not given any loan");
        }

        if(!validateIfAccountExistsInBank(bank, accountName)){
            throw new BankAccountMismatchException(accountName+" has got no loan from "+bankName);
        }

        Account account = bank.getAccount(accountName);
        if(emiNumber > account.getNumber_of_emi()){
            throw new Exception("emi number is invalid");
        }

        int amountPaidTillEmi = account.getEmiAmount()*emiNumber;
        int totalAmountPaid = amountPaidTillEmi+paymentAmount;
        int amountRemaining = account.getAmount() - totalAmountPaid;

        if(amountRemaining < 0){
            throw new Exception("Lump sum exceeded then remaining amount to be paid for "+accountName+" holding loan from "+bankName);
        }else{
           account.setLumpSumSchedule(emiNumber,paymentAmount);
        }
        account.setCurrentEmi(emiNumber);
    }

    public String getBalance(String bankName, String accountName, int emiNumber) throws Exception{
        Bank bank = banks.get(bankName);
        if(bank == null){
            throw new InvalidBankException(bankName+" is invalid");
        }

        if(!validateIfAccountExistsInBank(bank, accountName)){
            throw new BankAccountMismatchException(accountName+" has no loan in "+bankName);
        }

        Account account = bank.getAccount(accountName);
        int amountPaidInEmis = emiNumber*account.getEmiAmount();
        int totalLumpSumAmount = 0;
        Map<Integer,Integer> lumpSumSchedule = account.getLumpSumSchedule();
        for (int emi : lumpSumSchedule.keySet()) {
            if (emi > emiNumber) {
                break;
            }
            totalLumpSumAmount += lumpSumSchedule.get(emi);
        }
        int totalAmountRemaining = Math.max((account.getAmount() - (amountPaidInEmis + totalLumpSumAmount)), 0);
        double remainingEmis = totalAmountRemaining/(double)account.getEmiAmount();
        int remainingCeiledEmis = (int)Math.ceil(remainingEmis);

        return (bankName+" "+accountName+" "+(amountPaidInEmis + totalLumpSumAmount)+" "+remainingCeiledEmis);
    }

    private boolean validateIfAccountExistsInBank(Bank bank, String accountName){
        Account account = bank.getAccount(accountName);
        return account != null;
    }

}
