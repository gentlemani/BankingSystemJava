package com.rob.banking.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        Path file = Files.createFile(tempDir.resolve("test2.txt"));
        assertTrue(AccountRepository.fileExists(file.toString()));
    }
    @Test
    void createFile_createsNewFile() throws IOException {
        Path file = tempDir.resolve("test.txt");
        AccountRepository.createFile(file.toString());
        assertTrue(Files.isRegularFile(file));
    }


}