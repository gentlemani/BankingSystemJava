package com.rob.banking.utils;

import com.rob.banking.model.Account;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * Utility class for parsing input data into numbers, do not instantiate.
 *
 */
public final class AccountUtil {
    private AccountUtil() {
        throw new AssertionError("Utility class");
    }

    public static void printAccountDetails(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account is null");
        }
        System.out.println("------------------------------------------------------------------");
        System.out.print("Account number: ");
        System.out.println(account.getAccountNumber());
        System.out.print("Name: ");
        System.out.println(account.getOwnerName());
        System.out.print("Email: ");
        System.out.println(account.getOwnerEmail());
        System.out.print("Phone number: ");
        System.out.println(account.getOwnerPhoneNumber());
        System.out.print("Balance: ");
        System.out.println(account.getOwnerBalance());
        System.out.println("------------------------------------------------------------------");
    }
    public static boolean isBalanceZero(Account account){
        Objects.requireNonNull(account,"Account must not be null");
        return account.getOwnerBalance().compareTo(BigDecimal.ZERO) == 0;
    }
}
