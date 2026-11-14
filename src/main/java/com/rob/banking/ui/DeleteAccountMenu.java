package com.rob.banking.ui;


import com.rob.banking.model.Account;
import com.rob.banking.repository.AccountRepository;
import com.rob.banking.utils.AccountUtil;
import com.rob.banking.utils.InputValidatorUtil;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class for account deletion
 * <p> An account should have a zero balance to be deleted
 * <p> A confirmation message is implemented
 */
public final class DeleteAccountMenu {

    private final String YES = "y";
    private final String NO = "n";
    private final Scanner scanner;
    private final AccountRepository accountRepository;
    private Account account;

    public DeleteAccountMenu(Scanner scanner, AccountRepository accountRepository) {
        this.scanner = scanner;
        this.accountRepository = accountRepository;
    }

    public void menu() {
        boolean isValidInput;
        String accountNumber;

        System.out.println("*** Delete account ***");
        do {
            System.out.print("Please enter the account number to be deleted: ");
            accountNumber = scanner.nextLine();
            isValidInput = InputValidatorUtil.isValidNumber(accountNumber);
            if (!isValidInput) {
                System.out.println("Please enter a valid number");
            }
        } while (!isValidInput);
        try {
            this.account = accountRepository.findAccountUser(accountNumber);
            if (this.account == null) {
                System.out.println("Account not found");
                return;
            }
            AccountUtil.printAccountDetails(this.account);
            confirmationMenu();
        } catch (IOException e) {
            System.out.println("Failed to find account in the repository");
        }
    }

    /**
     * Asks the user for confirmation before deleting the account.
     */
    private void confirmationMenu() {
        String input;
        boolean isValidInput;
        do {
            System.out.println("Are you sure you want to delete this account? (y\\n): ");
            input = scanner.nextLine();
            isValidInput = isValidConfirmationOption(input);
            if (!isValidInput) {
                System.out.println("Please enter a valid option (y or n)");
            }
        } while (!isValidInput);

        if (input.trim().equalsIgnoreCase(NO)) {
            System.out.println("Account not deleted");
            return;
        }
        if (!AccountUtil.isBalanceZero(account)) {
            System.out.println("Balance is not zero");
            System.out.println("Please withdraw all the money first");
            return;
        }
        deleteAccount();
    }

    /**
     * Deletes account from repository.
     * <p> Action cannot be undone.
     */
    private void deleteAccount() {
        try {
            List<Account> accounts = accountRepository.getAllFileData();
            String accountNumber = account.getAccountNumber();
            boolean wasDeleted = accounts.removeIf(
                    existingAccount -> Objects.equals(existingAccount.getAccountNumber(), accountNumber));
            if (!wasDeleted) {
                System.out.println("Account not found");
                return;
            }
            accountRepository.updateRepositoryData(accounts);
            System.out.println("Account deleted");
        } catch (IOException e) {
            System.out.println("Failed to delete account");
        }

    }

    /**
     * Checks whether an input is a valid confirmation option.
     * <p> It is not case-sensitive.
     * <p> Valid inputs: {@code "y"}, {@code "n "}, {@code " Y"}, {@code " N "}.
     * <p> Invalid inputs: {@code "yes"}, {@code "No"},{@code ""}.
     *
     * @param input {@code String} With the user input.
     * @return {@code true} if the {@code input} is {@code y} or {@code n}, ignoring case and surrounding spaces.
     * <p> {@code false} otherwise.
     */
    private boolean isValidConfirmationOption(String input) {
        if (input == null) {
            return false;
        }
        String normalized = input.trim();
        return YES.equalsIgnoreCase(normalized) || NO.equalsIgnoreCase(normalized);
    }
}
