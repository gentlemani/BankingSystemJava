package com.rob.banking.ui;

import com.rob.banking.model.Account;
import com.rob.banking.utils.InputParserUtil;
import com.rob.banking.utils.InputValidatorUtil;

import java.util.OptionalInt;
import java.util.Scanner;

public class BankingMenu {
    private final Scanner scanner;

    public BankingMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void menuOptions() {
        String option;
        int choice;
        do {
            option = readMenuOptions();
            choice = executeMenuOption(option);
        } while (choice != MenuOptionEnum.EXIT.getCode());

    }

    private String readMenuOptions() {
        System.out.println("***** Welcome to Menu *****");
        for (MenuOptionEnum option : MenuOptionEnum.values()) {
            System.out.println(option.getCode() + ". " + option.getLabel());
        }
        return this.scanner.nextLine();
    }

    /**
     * Executes the select option by the user.
     *
     * @param input A string containing the option number.
     * @return The selected number option as {@code int}.
     * <p>In case it is not a valid option, it will return {@code -1}.
     */
    private int executeMenuOption(String input) {
        int option = validateIntegerInput(input);
        switch (option) {
            case 1:
                createAccountOption();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
        return option;
    }

    /**
     * Submenu for the creation of an account.
     */
    private void createAccountOption() {
        String name;
        String phoneNumber;
        String email;
        Boolean isvalidField;
        System.out.println("*** Account creation ***");
        System.out.println("Please enter account name");
        name = this.scanner.nextLine();
        do {

            System.out.println("Please enter your phone number");
            phoneNumber = this.scanner.nextLine();
            isvalidField = InputValidatorUtil.isValidPhoneNumber(phoneNumber);
            if(!isvalidField) {
                System.out.println("Please enter a valid phone number");
            }
        }while(!isvalidField);
        do {
            System.out.println("Please enter account email");
            email = this.scanner.nextLine();
            isvalidField = InputValidatorUtil.isValidEmail(email);
            if(!isvalidField) {
                System.out.println("Please enter a valid email");
            }
        }while(!isvalidField);



    }

    /**
     * Validates numbers given a string
     *
     * @param input A string containing a positive number
     * @return {@code -1} If it is not an integer.
     * <p> Otherwise returns the given integer.
     */
    private int validateIntegerInput(String input) {
        OptionalInt option = InputParserUtil.parseIntegerInput(input);
        if (option.isPresent()) {
            return option.getAsInt();
        }
        return -1;
    }


}
