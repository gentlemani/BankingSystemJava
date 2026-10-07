package com.rob.banking.service;

public enum TransactionStatus {
    ON_HOLD("On_Hold"),
    PROGRESS("Progress"),
    SUCCESS("Success"),
    FAILED("Failed");

    private final String status;
    TransactionStatus(String status) {
        this.status = status;
    }
    public String getStatus() {return status;}
}
