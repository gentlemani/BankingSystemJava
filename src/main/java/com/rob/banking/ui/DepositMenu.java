package com.rob.banking.ui;

import com.rob.banking.repository.AccountRepository;

import java.util.Scanner;

public final class DepositMenu {
    private DepositMenu() {
        throw new IllegalStateException("SubMenu class");
    }

    public static void menu(Scanner scanner, AccountRepository accountRepository) {
        System.out.println("*** Deposit Menu ***");
    }
}
