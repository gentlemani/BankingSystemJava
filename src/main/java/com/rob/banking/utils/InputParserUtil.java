package com.rob.banking.utils;

import java.util.OptionalInt;

/**
 * Utility class for parsing input data into numbers, do not instantiate.
 */
public final class InputParserUtil {
    /**
     * Avoid instantiation of the class
     */
    private InputParserUtil(){
        throw new AssertionError("Utility class, do not instantiate.");
    }

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

}
