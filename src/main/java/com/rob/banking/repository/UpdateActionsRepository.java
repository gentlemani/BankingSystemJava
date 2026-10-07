package com.rob.banking.repository;

public enum UpdateActionsRepository {
    updateName(0),
    updatePhoneNumber(1),
    updateEmail(2),
    updateAccountNumber(3),
    updateBalance(4);

    public final int value;

    UpdateActionsRepository(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
