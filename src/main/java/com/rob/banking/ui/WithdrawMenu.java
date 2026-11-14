package com.rob.banking.ui;

import com.rob.banking.model.Account;
import com.rob.banking.model.InsufficientFundsException;
import com.rob.banking.model.Transaction;
import com.rob.banking.model.TransactionType;
import com.rob.banking.repository.AccountRepository;
import com.rob.banking.service.TransactionService;
import com.rob.banking.utils.InputParserUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;

/**
 * Submenu class for withdrawing money from an account.
 * <p>Do not instantiate.
 */
public final class WithdrawMenu {
    private WithdrawMenu() {
        throw new IllegalStateException("SubMenu class");
    }

    public static void menu(Scanner scanner, AccountRepository accountRepository) {
        boolean isValidInput;
        OptionalInt input = OptionalInt.empty();
        String accountNumber = null;
        Optional<BigDecimal> amount;
        BigDecimal withdrawAmount = null;
        TransactionService transactionService = new TransactionService(accountRepository.getPath());


        System.out.println("*** Withdraw Menu ***");
        do {
            isValidInput = false;
            System.out.print("Please enter account number: ");
            input = InputParserUtil.parseIntegerInput(scanner.nextLine());
            if (input.isEmpty()) {
                System.out.println("Please enter a valid number");
            } else {
                accountNumber = String.valueOf(input.getAsInt());
                isValidInput = true;
            }
        } while (!isValidInput);

        do {
            isValidInput = false;
            System.out.print("Please enter the amount to be withdrawn: ");
            amount = InputParserUtil.parseBigDecimalInput(scanner.nextLine());
            if (amount.isEmpty()) {
                System.out.println(
                        "Please enter a valid amount.\n * A maximum 2 decimals is allowed.\n* Only positive numbers are accepted");
            } else {
                withdrawAmount = amount.get();
                isValidInput = true;
            }
        } while (!isValidInput);
        try {
            Account account = accountRepository.findAccountUser(accountNumber);
            if (account == null) {
                System.out.println("Account not found");
            } else {
                transactionService.makeTransaction(new Transaction(account, withdrawAmount, TransactionType.WITHDRAW));
                System.out.println("--- Transaction completed successfully ---");
            }
        } catch (IOException e) {
            System.out.println("An error occurred with the transaction");
        } catch (InsufficientFundsException e) {
            System.out.println("You cannot withdraw more than your available balance.");
        }
    }
}
