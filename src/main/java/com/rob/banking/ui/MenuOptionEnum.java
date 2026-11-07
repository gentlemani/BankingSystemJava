package com.rob.banking.ui;

public enum MenuOptionEnum {
    CREATE_ACCOUNT(1,"Create Account"),
    DEPOSIT(2,"Deposit"),
    WITHDRAW(3,"Withdraw"),
    ACCOUNT_DETAIL(4,"Account details"),
    EXIT(5, "Exit");

    private final int code;
    private final String label;

    MenuOptionEnum(int code, String label){
        this.code = code;
        this.label = label;
    }
    public int getCode() {
        return code;
    }
    public String getLabel() {
        return label;
    }
}
