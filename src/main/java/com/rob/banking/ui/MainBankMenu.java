package com.rob.banking.ui;

import com.rob.banking.repository.AccountRepository;
import com.rob.banking.utils.InputParserUtil;

import java.io.IOException;
import java.util.OptionalInt;
import java.util.Scanner;

public class MainBankMenu {
    private final Scanner scanner;
    private final AccountRepository accountRepository;

    public MainBankMenu(Scanner scanner, String repositoryPath) {
        this.scanner = scanner;
        this.accountRepository = new AccountRepository(repositoryPath);
        if(!accountRepository.fileExists()){
            try{
                accountRepository.createFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void menu() {
        String option;
        MenuOptionEnum choice;
        do {
            option = readMenuOptions();
            choice = executeMenuOption(option);
        } while (choice != MenuOptionEnum.EXIT);

    }

    private String readMenuOptions() {
        System.out.println("***** Welcome to Menu *****");
        for (MenuOptionEnum option : MenuOptionEnum.values()) {
            if(option == MenuOptionEnum.UNKNOWN_OPTION){
                continue;
            }
            System.out.println(option.getCode() + ". " + option.getLabel());
        }
        System.out.print("Option: ");
        return this.scanner.nextLine();
    }

    /**
     * Executes the select option by the user.
     *
     * @param input A string containing the option number.
     * @return The selected number option as {@code int}.
     * <p>In case it is not a valid option, it will return {@code -1}.
     */
    private MenuOptionEnum executeMenuOption(String input) {
        MenuOptionEnum option = validateIntegerInput(input);
        switch (option) {
            case MenuOptionEnum.CREATE_ACCOUNT:
                CreateAccountMenu.menu(this.scanner,this.accountRepository);
                break;
            case MenuOptionEnum.DEPOSIT:
                DepositMenu.menu(this.scanner,this.accountRepository);
                break;
            case MenuOptionEnum.WITHDRAW:
                WithdrawMenu.menu(this.scanner,this.accountRepository);
                break;
            case MenuOptionEnum.ACCOUNT_DETAIL:
                break;
            case MenuOptionEnum.EXIT:
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
        return option;
    }



    /**
     * Validates numbers given a string
     *
     * @param input A string containing a positive number
     * @return {@code -1} If it is not an integer.
     * <p> Otherwise returns the given integer.
     */
    private MenuOptionEnum validateIntegerInput(String input) {
        OptionalInt option = InputParserUtil.parseIntegerInput(input);
        if (option.isEmpty()) {
            return MenuOptionEnum.UNKNOWN_OPTION;
        }
        return MenuOptionEnum.fromCode(option.getAsInt());
    }


}
