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
import java.util.Objects;

import static com.rob.banking.repository.WriteOptionsRepository.*;

/**
 * Manages the file where all the data is stored
 */
public class AccountRepository {
    private String path;

    public AccountRepository(String path){
        this.path = path;
    }
    public void setPath(String path){this.path = path;}

    public String getPath() {
        return path;
    }

    /**
     * Checks whether a file exists at a specific path
     *
     * @return {@code true} if file exists,
     * {@code false} otherwise (including when it is a directory)
     */
    public boolean fileExists() {
        return Files.isRegularFile(Path.of(this.path));
    }

    /**
     * File creation at a specific path, including missing parent directories.
     *
     * @throws java.nio.file.FileAlreadyExistsException If the file already exists
     * @throws IOException                              If the file or its parents directories cannot be created
     */
    public void createFile() throws IOException {
        Path file = Path.of(this.path);
        Files.createDirectories(file.toAbsolutePath().getParent());
        Files.createFile(file);
    }

    /**
     * Saves new accounts to the repository as a new line
     *
     * @param account New account to save
     * @throws NoSuchFileException If the file does not exist
     * @throws IOException         Account not registered correctly to the repository
     */
    public void appendAccount(Account account) throws IOException {
        // Automatically printer is closed
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(this.path), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord(toCsvValues(account));
        }
    }

    /**
     * Reads all lines of the file.
     * <p> Every line is an account
     *
     * @return A list containing every stored account
     * @throws NoSuchFileException If the file does not exist
     * @throws IOException         If the file cannot be read
     */
    public List<Account> retrieveAllFileData() throws IOException {
        List<Account> accountsList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Path.of(this.path), StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.parse(reader)) {
            for (CSVRecord record : parser) {
                accountsList.add(new Account(record.get(0), record.get(1), record.get(2), record.get(3), new BigDecimal(record.get(4))));
            }
        }
        return accountsList;
    }

    /**
     * Retrieves user account class details given an account number.
     *
     * @param accountNumber Account number of the account to be found.
     * @return {@link Account} If the account is found.
     * {@code null} If the account is not found.
     * @throws NoSuchFileException If the file does not exist.
     * @throws IOException         If the file cannot be read.
     */
    public Account findAccountUser(String accountNumber) throws IOException {
        try (Reader reader = Files.newBufferedReader(Path.of(this.path), StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.parse(reader);) {
            for (CSVRecord record : parser) {
                if (record.get(3).equals(accountNumber)) {
                    return new Account(record.get(0), record.get(1), record.get(2), record.get(3), new BigDecimal(record.get(4)));
                }
            }
            return null;
        }
    }

    /**
     * Writes the accounts to an existing file, either replacing its content or
     * appending to it.
     *
     * @param accounts Accounts to be written into the file.
     * @param writeOptionsRepository Whether replace or append the file content.
     * @throws NoSuchFileException if The file does not exist.
     * @throws IOException         If something fails.
     */
    public void writeAccounts(List<Account> accounts, WriteOptionsRepository writeOptionsRepository ) throws IOException {
        Objects.requireNonNull(writeOptionsRepository, "WriteOptionsRepository cannot be null");
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(this.path), StandardCharsets.UTF_8, StandardOpenOption.WRITE, writeOptionsRepository.getOption()); CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (Account account : accounts) {
                printer.printRecord(toCsvValues(account));
            }
        }

    }


    /**
     * Converts an account values into one CSV line at a specific order.
     *
     * @param account Account to convert
     * @return values of the account in the order they are written to the file
     */
    private static Object[] toCsvValues(Account account) {
        return new Object[]{
                account.getOwnerName(),
                account.getOwnerPhoneNumber(),
                account.getOwnerEmail(),
                account.getAccountNumber(),
                account.getOwnerBalance()
        };
    }
}

