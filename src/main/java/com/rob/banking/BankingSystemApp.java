package com.rob.banking;

import com.rob.banking.ui.BankingMenu;

import java.util.Scanner;

/**
 * Application entry point
 */
public class BankingSystemApp {

    void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            BankingMenu menu = new BankingMenu(scanner,"/repository");
            menu.menuOptions();

        }

    }
}
