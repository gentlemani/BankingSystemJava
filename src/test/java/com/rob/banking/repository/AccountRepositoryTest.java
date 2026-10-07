package com.rob.banking.repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.rob.banking.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {
    @TempDir
    Path tempDir;

    AccountRepository accountRepository;

    @Test
    void fileExists_returnsFalseWhenFileIsMissing() {
        Path file = tempDir.resolve("test.txt");
        accountRepository = new AccountRepository(file.toString());
        assertFalse(accountRepository.fileExists());
    }

    @Test
    void fileExists_returnsTrueWhenFileIsNotMissing() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        accountRepository = new AccountRepository(file.toString());
        assertTrue(accountRepository.fileExists());
    }

    @Test
    void createFile_createsNewFile() throws IOException {
        Path file = tempDir.resolve("test.txt");
        accountRepository = new AccountRepository(file.toString());
        accountRepository.createFile();
        assertTrue(Files.isRegularFile(file));
    }

    @Test
    void appendAccount_writesAccountDetailsToFile() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        accountRepository = new AccountRepository(file.toString());
        Account account = new Account("gentle", "1231", "gentle@example.com","1",new BigDecimal("0"));
        accountRepository.appendAccount(account);
        List<String> lines = Files.readAllLines(file);
        assertEquals(1, lines.size());
        // accountNumber, name, phoneNumber, email, balance
        assertEquals("gentle,1231,gentle@example.com,1,0", lines.getFirst());
        String content = Files.readString(file);
        assertTrue(content.contains("gentle@example.com"));
    }

    @Test
    void retrieveAllFileData_returnsAllAccountsFromFile() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com","1",new BigDecimal("0"));
        Account account2 = new Account("gent", "8765", "man@example.com","2",new BigDecimal("50"));
        accountRepository.appendAccount(account);
        accountRepository.appendAccount(account2);

        List<Account> accountList = accountRepository.retrieveAllFileData();

        assertEquals("mani", accountList.getFirst().getOwnerName());
        assertEquals("1231", accountList.getFirst().getOwnerPhoneNumber());
        assertEquals("gentle@example.com", accountList.getFirst().getOwnerEmail());
        assertEquals("1", accountList.getFirst().getAccountNumber());
        assertEquals(new BigDecimal(0), accountList.getFirst().getOwnerBalance());
        assertEquals(new BigDecimal("50"), accountList.get(1).getOwnerBalance());
    }
    @Test
    void findAccountUser_returnsAccountInformation() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        accountRepository = new AccountRepository(file.toString());

        Account account = new Account("mani", "1231", "gentle@example.com","1",new BigDecimal("0"));
        Account account2 = new Account("gent", "8765", "man@example.com","3",new BigDecimal("50"));
        accountRepository.appendAccount(account);
        accountRepository.appendAccount(account2);

        Account accountFromFile = accountRepository.findAccountUser("3");
        Account accountNotFounded = accountRepository.findAccountUser("6");

        assertNotSame(null,accountFromFile);
        assertNull(accountNotFounded);
        assert accountFromFile != null;
        assertEquals(new BigDecimal("50"), accountFromFile.getOwnerBalance());
        assertEquals("gent", accountFromFile.getOwnerName());
        assertEquals("man@example.com", accountFromFile.getOwnerEmail());
        assertEquals("8765", accountFromFile.getOwnerPhoneNumber());
    }
    @Test
    void writeAccounts_writesAccountsInTheRepositoryWithTruncate() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        accountRepository = new AccountRepository(file.toString());
        Account account = new Account("mani", "1231", "gentle@example.com","1",new BigDecimal("0"));
        Account account2 = new Account("gent", "8765", "man@example.com","2",new BigDecimal("50"));
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accounts.add(account2);

        accountRepository.writeAccounts(accounts,WriteOptionsRepository.TRUNCATE_EXISTING_OPTION);
        Account accountFromFile = accountRepository.findAccountUser("2");
        assertNotSame(null,accountFromFile);
        assert accountFromFile != null;
        assertEquals(new BigDecimal("50"), accountFromFile.getOwnerBalance());
        assertEquals("gent", accountFromFile.getOwnerName());
        assertEquals("man@example.com", accountFromFile.getOwnerEmail());
        assertEquals("8765", accountFromFile.getOwnerPhoneNumber());
    }

    @Test
    void writeAccounts_writesAccountsInTheRepositoryWithAppend() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        accountRepository = new AccountRepository(file.toString());

        Account account = new Account("le", "6732", "le@example.com","1",new BigDecimal("80"));
        Account account2 = new Account("mani", "1231", "gentle@example.com","2",new BigDecimal("0"));
        Account account3 = new Account("gent", "8765", "man@example.com","3",new BigDecimal("50"));
        List<Account> accounts = new ArrayList<>();
        accounts.add(account2);
        accounts.add(account3);

        accountRepository.appendAccount(account);
        accountRepository.writeAccounts(accounts,WriteOptionsRepository.APPEND_OPTION);

        Account leAccount = accountRepository.findAccountUser("1");
        Account accountFromFile = accountRepository.findAccountUser("3");

        assertNotSame(null,accountFromFile);
        assertNotSame(null,leAccount);
        assert accountFromFile != null;
        assert leAccount != null;

        assertEquals("le", leAccount.getOwnerName());

        assertEquals(new BigDecimal("50"), accountFromFile.getOwnerBalance());
        assertEquals("gent", accountFromFile.getOwnerName());
        assertEquals("man@example.com", accountFromFile.getOwnerEmail());
        assertEquals("8765", accountFromFile.getOwnerPhoneNumber());
    }

}