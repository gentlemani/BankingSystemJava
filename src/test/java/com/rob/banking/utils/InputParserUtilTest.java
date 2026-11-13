package com.rob.banking.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InputParserUtilTest {

    // parseIntegerInput
    @ParameterizedTest
    @ValueSource(strings = {"20"," 42 ","653252","-1","-328"})
    void parseIntegerInput_acceptsValidInputs(String input){
        assertTrue(InputParserUtil.parseIntegerInput(input).isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"20a","42.1","65322.","cien"})
    void parseIntegerInput_rejectsInvalidInputs(String input){
        assertTrue(InputParserUtil.parseIntegerInput(input).isEmpty());
    }
    @Test
    void parseIntegerInput_rejectsNullInput(){
        assertTrue(InputParserUtil.parseIntegerInput(null).isEmpty());
    }

    // parseBigDecimalInput
    @ParameterizedTest
    @ValueSource(strings = {"20","42.01","653252.80"})
    void parseBigDecimalInput_acceptsValidInputs(String input){
        assertTrue(InputParserUtil.parseBigDecimalInput(input).isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"20.","42.01.","62.003","cien","-1","-328"})
    void parseBigDecimalInput_rejectsInvalidInputs(String input){
        assertTrue(InputParserUtil.parseBigDecimalInput(input).isEmpty());
    }

    @Test
    void parseBigDecimalInput_rejectsNullInput(){
        assertTrue(InputParserUtil.parseBigDecimalInput(null).isEmpty());
    }

}