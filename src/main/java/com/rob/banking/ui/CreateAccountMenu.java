package com.rob.banking.ui;

import com.rob.banking.model.Account;
import com.rob.banking.repository.AccountRepository;
import com.rob.banking.utils.InputValidatorUtil;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Submenu for the creation of an account.
 * <p> Do not instantiate.
 */
public final class CreateAccountMenu {

    private CreateAccountMenu() {
        throw new IllegalStateException("SubMenu class");
    }

    /**
     * Menu for account creation.
     * @param scanner Text input entry point.
     * @param accountRepository Repository in which data is going to be saved.
     */
    public static void menu(Scanner scanner,AccountRepository accountRepository) {
        String name;
        String phoneNumber;
        String email;
        boolean isValidField;
        System.out.println("*** Account creation ***");
        System.out.println("Please enter account name");
        name = scanner.nextLine();
        do {

            System.out.println("Please enter your phone number");
            phoneNumber = scanner.nextLine();
            isValidField = InputValidatorUtil.isValidPhoneNumber(phoneNumber);
            if (!isValidField) {
                System.out.println("Please enter a valid phone number (10 digits)");
            }
        } while (!isValidField);
        do {
            System.out.println("Please enter account email");
            email = scanner.nextLine();
            isValidField = InputValidatorUtil.isValidEmail(email);
            if (!isValidField) {
                System.out.println("Please enter a valid email");
            }
        } while (!isValidField);
        try {
            String lastId = accountRepository.getLastId();
            int newId = Integer.parseInt(lastId) + 1;

            Account account = new Account(name, phoneNumber, email, String.valueOf(newId), BigDecimal.ZERO);
            accountRepository.appendAccount(account);

        } catch (IOException e) {
            System.out.println("Error while reading file");
        }


    }
}
