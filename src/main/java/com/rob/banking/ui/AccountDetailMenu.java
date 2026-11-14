package com.rob.banking.ui;

import com.rob.banking.model.Account;
import com.rob.banking.repository.AccountRepository;
import com.rob.banking.utils.AccountUtil;
import com.rob.banking.utils.InputParserUtil;
import com.rob.banking.utils.InputValidatorUtil;

import java.io.IOException;
import java.util.OptionalInt;
import java.util.Scanner;

public final class AccountDetailMenu {
    private AccountDetailMenu() {
        throw new AssertionError("SubMenu class");
    }

    public static void menu(Scanner scanner, AccountRepository accountRepository) {
        boolean isValidInput;
        String accountNumber;
        OptionalInt inputValue;
        Account account;
        System.out.println("*** Account Detail ***");
        do {
            isValidInput = false;
            System.out.println("Please enter account number: ");
            accountNumber = scanner.nextLine();
            if (InputValidatorUtil.isValidNumber(accountNumber)) {
                isValidInput = true;
            } else {
                System.out.println("Please enter a valid number");
            }
        } while (!isValidInput);
        try {
            account = accountRepository.findAccountUser(accountNumber);
            if (account == null) {
                System.out.println("Account not found");
            } else {
                AccountUtil.printAccountDetails(account);
            }
        } catch (IOException e) {
            System.out.println("An error occurred with the repository");
        }
    }

}
