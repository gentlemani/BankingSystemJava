package com.rob.banking.service;


import com.rob.banking.model.Account;
import com.rob.banking.model.Transaction;
import com.rob.banking.repository.AccountRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Manages user account details such as balance changes.
 */
public class TransactionService {
    private AccountRepository accountRepository;
    private final String path;

    public TransactionService(String path) throws IOException {
        this.path = path;
        accountRepository = new AccountRepository(this.path);
        if (!accountRepository.fileExists()) {
            accountRepository.createFile();
        }

    }

    public void makeTransaction(Transaction transaction, Account account) throws IOException {
        List<Account> accountList = accountRepository.retrieveAllFileData();
        for(int i=0;accountList.size() > i; i++) {
            if (Objects.equals(accountList.get(i).getAccountNumber(), account.getAccountNumber())) {
                accountList.set(i, account);
                break;
            }

        }
        // Update repository method

    }

}
