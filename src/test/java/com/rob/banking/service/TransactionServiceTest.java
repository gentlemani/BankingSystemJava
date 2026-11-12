package com.rob.banking.service;

import com.rob.banking.model.Account;
import com.rob.banking.model.Transaction;
import com.rob.banking.model.TransactionType;
import com.rob.banking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Depends on account and repository creation
 */
class TransactionServiceTest {
    @TempDir
    Path tempDir;

    @Test
    void makeTransactionTest_DepositIllegalAccountBalance() throws IOException {
        //Repository creation and data insertion
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        AccountRepository accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com", "1", new BigDecimal("0"));
        accountRepository.appendAccount(account);

        Transaction transaction = new Transaction(account, new BigDecimal("-16"), TransactionType.DEPOSIT);
        TransactionService transactionservice = new TransactionService(file.toString());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionservice.makeTransaction(transaction));
        assertEquals( "Amount must be greater than zero",exception.getMessage());
    }

    @Test
    void makeTransactionTest_DepositAccountBalance() throws IOException {
        //Repository creation and data insertion
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        AccountRepository accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com", "1", new BigDecimal("0"));
        accountRepository.appendAccount(account);

        Transaction transaction = new Transaction(account, new BigDecimal("28"), TransactionType.DEPOSIT);
        TransactionService transactionservice = new TransactionService(file.toString());

        //Checks before transaction is done
        List<Account> accounts = accountRepository.getAllFileData();
        assertEquals(BigDecimal.ZERO,accounts.getFirst().getOwnerBalance());


        transactionservice.makeTransaction(transaction);
        accounts = accountRepository.getAllFileData();
        assertEquals(1,accounts.size());
        assertEquals(new BigDecimal("28"),accounts.getFirst().getOwnerBalance());
        assertEquals("1",accounts.getFirst().getAccountNumber());

    }
    @Test
    void makeTransactionTest_WithdrawNegativeAmountFromAccount()throws IOException{
        //Repository creation and data insertion
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        AccountRepository accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com", "1", new BigDecimal("1"));
        accountRepository.appendAccount(account);

        Transaction transaction = new Transaction(account, new BigDecimal("-1"), TransactionType.WITHDRAW);
        TransactionService transactionservice = new TransactionService(file.toString());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionservice.makeTransaction(transaction));
        assertEquals( "Amount must be greater than zero",exception.getMessage());
    }

    @Test
    void makeTransactionTest_WithdrawInsufficientBalanceFromAccount() throws IOException{
        //Repository creation and data insertion
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        AccountRepository accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com", "1", new BigDecimal("1"));
        accountRepository.appendAccount(account);

        Transaction transaction = new Transaction(account, new BigDecimal("2"), TransactionType.WITHDRAW);
        TransactionService transactionservice = new TransactionService(file.toString());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionservice.makeTransaction(transaction));
        assertEquals( "Insufficient balance",exception.getMessage());
    }

    @Test
    void makeTransactionTest_WithdrawSameBalanceFromAccount() throws IOException{
        //Repository creation and data insertion
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        AccountRepository accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com", "9", new BigDecimal("87"));
        accountRepository.appendAccount(account);

        Transaction transaction = new Transaction(account, new BigDecimal("87"), TransactionType.WITHDRAW);
        TransactionService transactionservice = new TransactionService(file.toString());

        //Checks before transaction is done
        List<Account> accounts = accountRepository.getAllFileData();
        assertEquals(new BigDecimal("87"),accounts.getFirst().getOwnerBalance());


        transactionservice.makeTransaction(transaction);
        accounts = accountRepository.getAllFileData();
        assertEquals(1,accounts.size());
        assertEquals(BigDecimal.ZERO,accounts.getFirst().getOwnerBalance());
        assertEquals("9",accounts.getFirst().getAccountNumber());
    }

    @Test
    void makeTransactionTest_WithdrawMoneyFromAccount() throws IOException{
        //Repository creation and data insertion
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        AccountRepository accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com", "9", new BigDecimal("80"));
        accountRepository.appendAccount(account);

        Transaction transaction = new Transaction(account, new BigDecimal("20"), TransactionType.WITHDRAW);
        TransactionService transactionservice = new TransactionService(file.toString());

        //Checks before transaction is done
        List<Account> accounts = accountRepository.getAllFileData();
        assertEquals(new BigDecimal("80"),accounts.getFirst().getOwnerBalance());


        transactionservice.makeTransaction(transaction);
        accounts = accountRepository.getAllFileData();
        assertEquals(1,accounts.size());
        assertEquals(new BigDecimal("60"),accounts.getFirst().getOwnerBalance());
        assertEquals("9",accounts.getFirst().getAccountNumber());
    }

}