package com.rob.banking.ui;

public final class WithdrawMenu {
    private WithdrawMenu() {
        throw new IllegalStateException("SubMenu class");
    }

    public static void menu(){
        System.out.println("*** Withdraw Menu ***");
    }
}
