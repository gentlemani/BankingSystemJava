package com.rob.banking.repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.rob.banking.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {
    @TempDir
    Path tempDir;

    @Test
    void fileExists_returnsFalseWhenFileIsMissing() {
        Path file = tempDir.resolve("test.txt");
        assertFalse(AccountRepository.fileExists(file.toString()));
    }

    @Test
    void fileExists_returnsTrueWhenFileIsNotMissing() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        assertTrue(AccountRepository.fileExists(file.toString()));
    }

    @Test
    void createFile_createsNewFile() throws IOException {
        Path file = tempDir.resolve("test.txt");
        AccountRepository.createFile(file.toString());
        assertTrue(Files.isRegularFile(file));
    }

    @Test
    void appendAccount_writesAccountDetailsToFile() throws IOException {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        Account account = new Account("gentle", "1231", "gentle@example.com","1",new BigDecimal("0"));
        AccountRepository.appendAccount(account, file.toString());
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
        Account account = new Account("mani", "1231", "gentle@example.com","1",new BigDecimal("0"));
        Account account2 = new Account("gent", "8765", "man@example.com","2",new BigDecimal("50"));
        AccountRepository.appendAccount(account, file.toString());
        AccountRepository.appendAccount(account2, file.toString());

        List<Account> accountList = AccountRepository.retrieveAllFileData(file.toString());

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
        Account account = new Account("mani", "1231", "gentle@example.com","1",new BigDecimal("0"));
        Account account2 = new Account("gent", "8765", "man@example.com","3",new BigDecimal("50"));
        AccountRepository.appendAccount(account, file.toString());
        AccountRepository.appendAccount(account2, file.toString());

        Account accountFromFile = AccountRepository.findAccountUser(file.toString(),"3");
        Account accountNotFounded = AccountRepository.findAccountUser(file.toString(),"6");

        assertNotSame(null,accountFromFile);
        assertNull(accountNotFounded);
        assert accountFromFile != null;
        assertEquals(new BigDecimal("50"), accountFromFile.getOwnerBalance());
        assertEquals("gent", accountFromFile.getOwnerName());
        assertEquals("man@example.com", accountFromFile.getOwnerEmail());
        assertEquals("8765", accountFromFile.getOwnerPhoneNumber());
    }


}