package com.rob.banking.utils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.regex.Pattern;

/**
 * Utility class for parsing input data into numbers, do not instantiate.
 * <p> Avoid instantiation of the class.
 */
public final class InputParserUtil {



    /**
     * Pattern that matches a non-negative amount with an optional decimal part of one or two  digits.
     * <p>Valid examples {@code 2043.21}, {@code 2.01}, {@code 232}.
     * <p>Invalid examples {@code 2043.231},{@code -2},{@code 2,3},{@code 2043.}.
     */
    private static final Pattern MONEY_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");


    private InputParserUtil(){
        throw new AssertionError("Utility class, do not instantiate.");
    }

    /**
     * Validates input to be an int type value.
     * <p>
     * @param input Expects {@code int} type.
     * @return Returns an {@link OptionalInt} type with the value.
     * <p> In case it is not a valid {@code int} value, it will return an empty {@link OptionalInt}.
     */
    public static OptionalInt parseIntegerInput(String input){
        if (input == null || input.isEmpty()){
            return OptionalInt.empty();
        }
        try {
            int number = Integer.parseInt(input.trim());
            return OptionalInt.of(number);
        }catch (NumberFormatException e){
            return OptionalInt.empty();
        }
    }

    /**
     * Parses a user input as a {@link BigDecimal}
     * @param input Value to be validated.
     * @return The parsed value, or an empty {@link Optional} if the input is not valid or the value is less than zero.
     */
    public static Optional<BigDecimal> parseBigDecimalInput(String input){
        if (input == null || !MONEY_PATTERN.matcher(input.trim()).matches()){
            return Optional.empty();
        }
        BigDecimal amount = new BigDecimal(input.trim());
        // Allows only zero or positive values
        return amount.signum() >= 0 ? Optional.of(amount) : Optional.empty();
    }

}
