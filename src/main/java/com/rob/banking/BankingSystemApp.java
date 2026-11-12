package com.rob.banking;

import com.rob.banking.ui.MainBankMenu;

import java.util.Scanner;

/**
 * Application entry point
 */
public class BankingSystemApp {

    void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            MainBankMenu menu = new MainBankMenu(scanner, "repository.txt");
            menu.menu();

        }

    }
}
