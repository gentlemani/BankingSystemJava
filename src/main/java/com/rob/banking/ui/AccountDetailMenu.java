package com.rob.banking.ui;

import com.rob.banking.model.Account;
import com.rob.banking.repository.AccountRepository;
import com.rob.banking.utils.InputParserUtil;

import java.io.IOException;
import java.util.OptionalInt;
import java.util.Scanner;

public final class AccountDetailMenu {
    private AccountDetailMenu(){
        throw new IllegalStateException("SubMenu class");
    }
    public static void menu(Scanner scanner, AccountRepository accountRepository){
        boolean isValidInput;
        String accountNumber;
        OptionalInt inputValue;
        Account account;
        System.out.println("*** Account Detail ***");
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
        try {
            account = accountRepository.findAccountUser(accountNumber);
            if(account == null){
                System.out.println("Account not found");
            }else{
                printAccountDetails(account);
            }
        }catch (IOException e){
            System.out.println("An error occurred with the repository");
        }
    }
    private static void printAccountDetails(Account account){
        if(account == null){
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
}
