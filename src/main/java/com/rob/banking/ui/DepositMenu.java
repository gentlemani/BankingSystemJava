package com.rob.banking.ui;

import com.rob.banking.model.Account;
import com.rob.banking.model.Transaction;
import com.rob.banking.model.TransactionType;
import com.rob.banking.repository.AccountRepository;
import com.rob.banking.service.TransactionService;
import com.rob.banking.utils.InputParserUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;

public final class DepositMenu {
    private DepositMenu() {
        throw new IllegalStateException("SubMenu class");
    }

    public static void menu(Scanner scanner, AccountRepository accountRepository) {
        String accountNumber;
        BigDecimal depositAmount = null;
        boolean isValidInput;
        OptionalInt inputValue;
        Account account;
        TransactionService transactionService = new TransactionService(accountRepository.getPath());
        Optional<BigDecimal> amount;

        System.out.println("*** Deposit Menu ***");
        do {
            isValidInput = false;
            System.out.println("Please enter account number: ");
            accountNumber = scanner.nextLine();
            inputValue = InputParserUtil.parseIntegerInput(accountNumber);
            if (inputValue.isEmpty()) {
                System.out.println("Please enter a valid number");
            } else {
                accountNumber = String.valueOf(inputValue.getAsInt());
                isValidInput = true;
            }
        } while (!isValidInput);

        do {
            isValidInput = false;
            System.out.print("Please enter the amount to be deposited: ");
            amount = InputParserUtil.parseBigDecimalInput(scanner.nextLine());
            if (amount.isEmpty()) {
                System.out.println(
                        "Please enter a valid number.\n* A maximum 2 decimals is allowed.\n* Only positive numbers are accepted");
            } else {
                depositAmount = amount.get();
                isValidInput = true;
            }
        } while (!isValidInput);

        try {
            account = accountRepository.findAccountUser(accountNumber);
            if (account == null) {
                System.out.println("Account not found");
            } else {
                transactionService.makeTransaction(new Transaction(account, depositAmount, TransactionType.DEPOSIT));
                System.out.println("--- Transaction completed successfully ---");
            }
        } catch (IOException e) {
            System.out.println("A problem occurred during the transaction");
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a valid amount.\n You cannot withdraw more than your available balance");
        }
    }
}
