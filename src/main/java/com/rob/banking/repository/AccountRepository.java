package com.rob.banking.repository;

import com.rob.banking.model.Account;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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
     * @param path    Location of the file
     * @throws NoSuchFileException If the file does not exist
     * @throws IOException         Account not registered correctly to the repository
     */
    public static void appendAccount(Account account, String path) throws IOException {
        // Automatically printer is closed
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord(account.getOwnerName(), account.getOwnerPhoneNumber(), account.getOwnerEmail(), account.getAccountNumber(), account.getOwnerBalance());
        }
    }

    /**
     * Reads all lines of the file.
     * <p> Every line is an account
     *
     * @param path Location of the file
     * @return A list containing every stored account
     * @throws NoSuchFileException If the file does not exist
     * @throws IOException If the file cannot be read
     */
    public static List<Account> retrieveAllFileData(String path) throws IOException {
        List<Account> accountsList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.parse(reader)) {
            for (CSVRecord record : parser) {
                accountsList.add(new Account(record.get(0), record.get(1),record.get(2),record.get(3), new BigDecimal(record.get(4))));
            }
        }
        return accountsList;
    }

    /**
     * Retrieves user account class details given a number account
     * @param path Location of the file
     * @param accountNumber Account number of the account to be found
     * @return {@code Account} If the account is found
     *         {@code null} If the account is not found
     * @throws NoSuchFileException If the file does not exist
     * @throws IOException If the file cannot be read
     */
    public static Account findAccountUser(String path,String accountNumber) throws IOException {
        try(Reader reader = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
            CSVParser parser = CSVFormat.DEFAULT.parse(reader);){
            for (CSVRecord record : parser) {
                if (record.get(3).equals(accountNumber)) {
                    return new Account(record.get(0), record.get(1),record.get(2),record.get(3), new BigDecimal(record.get(4)));
                }
            }
            return null;
        }
    }

}

