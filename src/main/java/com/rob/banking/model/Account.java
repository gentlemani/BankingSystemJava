package com.rob.banking.model;

import java.math.BigDecimal;

/**
 * Class that represents a bank account belonged to a single user
 * Tracks balance, owner details and account identifier
 */
public class Account {
    private String accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;

    public Account(String name, String phoneNumber, String email, String accountNumber, BigDecimal balance) {
        this.ownerName = name;
        this.ownerPhoneNumber = phoneNumber;
        this.ownerEmail = email;
        this.accountNumber = accountNumber;
        this.balance = balance;
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

    protected void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected void setOwnerBalance(BigDecimal balance) {
        this.balance = balance;
    }

    protected void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    protected void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    protected void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }
}