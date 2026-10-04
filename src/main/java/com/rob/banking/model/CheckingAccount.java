package com.rob.banking.model;

/**
 * Deposit, withdraw money from your account
 */
public class CheckingAccount extends Account {
    public CheckingAccount(Account account) {
        super(account.getOwnerName(), account.getOwnerPhoneNumber(), account.getOwnerEmail(), account.getAccountNumber(), account.getOwnerBalance());
    }
}
