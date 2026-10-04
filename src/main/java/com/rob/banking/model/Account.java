package com.rob.banking.model;

import java.math.BigDecimal;

/**
 * Class that represents a bank account belonged to a single user
 * Tracks balance, owner details and account identifier
 */
public class Account {
    private String accountNumber = "1";
    private BigDecimal balance = BigDecimal.ZERO;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;

    public Account(String name, String phoneNumber, String email) {
        this.ownerName = name;
        this.ownerPhoneNumber = phoneNumber;
        this.ownerEmail = email;
        // function that assigns a unique account number
        // this.accountNumber = getNextAccountNumber();

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getOwnerBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

}