package com.rob.banking.ui;

import com.rob.banking.utils.InputParserUtil;

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

    private void createAccountOption() {

        System.out.println("*** Account creation ***");
        System.out.println("Please enter account name");
        this.scanner.nextLine();

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
