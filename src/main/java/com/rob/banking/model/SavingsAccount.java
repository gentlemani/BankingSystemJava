package com.rob.banking.model;
// Get money by saving certain amount of money
public class SavingsAccount extends Account{
    public SavingsAccount(Account account) {
        super(account.getOwnerName(), account.getOwnerPhoneNumber(), account.getOwnerEmail(), account.getAccountNumber(), account.getOwnerBalance());
    }
}
