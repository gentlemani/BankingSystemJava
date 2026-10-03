package com.rob.banking.repository;

import com.rob.banking.model.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Manages the file where all the data is stored
 */
public class AccountRepository {

    /**
     * Checks whether a file exists at a specific path
     *
     * @param path Location of the file
     * @return {@code true} if file exists,
     *         {@code false} otherwise (including when it is a directory)
     */
    public static boolean fileExists(String path) {
        return Files.isRegularFile(Path.of(path));
    }

    /**
     * File creation at a specific path, including missing parent directories.
     *
     * @param path Location of the new file
     * @throws java.nio.file.FileAlreadyExistsException If the file already exists
     * @throws IOException If the file or its parents directories cannot be created
     */
    public static void createFile(String path)throws IOException {
        Path file = Path.of(path);
        Files.createDirectories(file.toAbsolutePath().getParent());
        Files.createFile(file);
    }
    public static void saveAccount(Account account) throws IOException {

    }
}
