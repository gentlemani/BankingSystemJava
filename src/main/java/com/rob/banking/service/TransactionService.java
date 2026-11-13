package com.rob.banking.service;


import com.rob.banking.model.Account;
import com.rob.banking.model.Transaction;
import com.rob.banking.model.TransactionType;
import com.rob.banking.repository.AccountRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Manages user account details such as balance changes.
 */
public class TransactionService {
    private final AccountRepository accountRepository;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public TransactionService(String path){
        this.accountRepository = new AccountRepository(path);
    }

    public void makeTransaction(Transaction transaction) throws IOException {
        try{
            // One transaction at a time
            LOCK.lock();

            List<Account> accountList = accountRepository.getAllFileData();
            BigDecimal amount = transaction.getAmount();
            Account account = transaction.getAccount();
            switch(transaction.getType()) {
                case TransactionType.DEPOSIT:
                    account.deposit(amount);
                    break;
                case TransactionType.WITHDRAW:
                    account.withdraw(amount);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid transaction option");
            }

            for(int i=0;accountList.size() > i; i++) {
                if (Objects.equals(accountList.get(i).getAccountNumber(), account.getAccountNumber())) {
                    //Replaces original balance
                    accountList.set(i, account);
                    break;
                }
            }
            accountRepository.updateRepositoryData(accountList);
        }finally {
            LOCK.unlock();
        }


    }

}
