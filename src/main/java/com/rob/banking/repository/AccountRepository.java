package com.rob.banking.repository;

import com.rob.banking.model.Account;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Manages the file where all the data is stored
 */
public class AccountRepository {

    /**
     * Checks whether a file exists at a specific path
     *
     * @param path Location of the file
     * @return {@code true} if file exists,
     * {@code false} otherwise (including when it is a directory)
     */
    public static boolean fileExists(String path) {
        return Files.isRegularFile(Path.of(path));
    }

    /**
     * File creation at a specific path, including missing parent directories.
     *
     * @param path Location of the new file
     * @throws java.nio.file.FileAlreadyExistsException If the file already exists
     * @throws IOException                              If the file or its parents directories cannot be created
     */
    public static void createFile(String path) throws IOException {
        Path file = Path.of(path);
        Files.createDirectories(file.toAbsolutePath().getParent());
        Files.createFile(file);
    }

    /**
     * Saves new accounts to the repository as a new line
     *
     * @param account New account to save
     * @param path    Location of the new file
     * @throws NoSuchFileException If the file does not exist
     * @throws IOException         Account not registered correctly to the repository
     */
    public static void appendAccount(Account account, String path) throws IOException {
        // Automatically printer is closed
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord(account.getAccountNumber(), account.getOwnerName(), account.getOwnerPhoneNumber(), account.getOwnerEmail(), account.getOwnerBalance());
        }
    }

}
