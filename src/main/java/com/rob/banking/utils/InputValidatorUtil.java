package com.rob.banking.utils;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Utility class with validation methods for user input fields.
 *
 * <p> This class cannot be instantiated.
 */
public final class InputValidatorUtil {
    private InputValidatorUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Validates a {@code String} to be a valid 10 digits phone number.
     *
     * @param phoneNumber {@code String} containing a 10 digits phone number.
     * @return {@code true} if all the {@code phoneNumber} is a valid 10 digits number.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    /**
     * Validates a {@code String} to be a valid email.
     *
     * @param email {@code String} containing a valid email.
     * @return {@code true} if all the {@code String} follows the correct email pattern.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        final Pattern PHONE_PATTERN = Pattern.compile("^[\\w.-]+@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        return PHONE_PATTERN.matcher(email).matches();
    }

    public static boolean isValidNumber(String input) {
        if (input == null || input.trim().isEmpty()) {
        return false;
        }
        final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
        return NUMBER_PATTERN.matcher(input).matches();
    }
}
