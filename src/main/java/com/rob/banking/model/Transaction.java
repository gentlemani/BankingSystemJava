package com.rob.banking.model;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.Instant;

/**
 *
 */
public class Transaction {

    private final Account account;
    private final BigDecimal amount;
    private final TransactionType type;
    private final String token;


    public Transaction(Account account, BigDecimal amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.token = tokenGenerator();
    }
    private String tokenGenerator(){
        SecureRandom secureRandom = new SecureRandom();
        long timestamp = Instant.now().toEpochMilli();
        String tokenString = Long.toString(timestamp);
        String randomCharacter = String.format("%06d",secureRandom.nextInt(100001));
        return tokenString + randomCharacter;
    }
    public Account getAccount() {
        return account;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public TransactionType getType() {
        return type;
    }
    public String getToken() {return token;}

}
