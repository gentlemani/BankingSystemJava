package com.rob.banking.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class that represents a bank account belonged to a single user
 * Tracks balance, owner details and account identifier
 */
public class Account {
    private final String accountNumber;
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

    public void deposit(BigDecimal amount) {
        requirePositiveValue(amount);
        balance = balance.add(amount);
    }

    /**
     * Safely withdraw money from the account avoiding negative balance.
     * @param amount Amount of money to be subtracted.
     */
    public void withdraw(BigDecimal amount) {
        avoidNegativeBalance(this.balance, amount);
        balance = balance.subtract(amount);
    }

    /**
     * Avoids a negative balance in the account given an amount to be subtracted.
     * @param currentBalance Current account balance to be compared.
     * @param amountToWithdraw Amount of money to be subtracted. It must be a positive quantity (includes zero).
     */
    private static void avoidNegativeBalance(BigDecimal currentBalance,BigDecimal amountToWithdraw) {
        Objects.requireNonNull(amountToWithdraw);
        requirePositiveValue(amountToWithdraw);
        if(currentBalance.compareTo(amountToWithdraw) < 0){
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    /**
     * Checks whether the amount is a positive number (zero included) or negative. Throws an
     * exception in case it is negative.
     * @param amount Amount of money to be subtracted.
     */
    private static void requirePositiveValue(BigDecimal amount) {
        Objects.requireNonNull(amount);
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}