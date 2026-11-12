package com.rob.banking.ui;

public enum MenuOptionEnum {
    UNKNOWN_OPTION(-1,"Invalid option"),
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

    /**
     * Finds the given code that matches
     * @param code One of the available options
     * @return The matching option, or {@link #UNKNOWN_OPTION} if there is no match.
     */
    public static MenuOptionEnum fromCode(int code){
        for(MenuOptionEnum option : MenuOptionEnum.values()){
            if(option.getCode() == code){
                return option;
            }

        }
        return UNKNOWN_OPTION;
    }
}
