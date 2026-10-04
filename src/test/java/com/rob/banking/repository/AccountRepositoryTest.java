package com.rob.banking.repository;

import java.io.IOException;
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
        Account account = new Account("gentle", "1231", "gentle@example.com");
        AccountRepository.appendAccount(account, file.toString());
        List<String> lines = Files.readAllLines(file);
        assertEquals(1, lines.size());
        // accountNumber, name, phoneNumber, email, balance
        assertEquals("1,gentle,1231,gentle@example.com,0", lines.getFirst());
        String content = Files.readString(file);
        assertTrue(content.contains("gentle@example.com"));
    }


}