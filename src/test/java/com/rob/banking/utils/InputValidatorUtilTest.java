package com.rob.banking.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorUtilTest {
    @Test
    void isValidEmail_returnsTrueIfEmailIsValid()
    {
        assertTrue(InputValidatorUtil.isValidEmail("gen@gmal.don"));
        assertTrue(InputValidatorUtil.isValidEmail("1_k@g.do"));
        assertTrue(InputValidatorUtil.isValidEmail("gen-12@gm321.dons"));

    }
    @Test
    void isValidEmail_returnsFalseIfEmailIsNotValid()
    {
        assertFalse(InputValidatorUtil.isValidEmail("gen@gmal"));
        assertFalse(InputValidatorUtil.isValidEmail("gen@gmal."));
        assertFalse(InputValidatorUtil.isValidEmail("gen@.com"));
    }
    @Test
    void isValidPhoneNumber_returnsTrueIfPhoneNumberIsValid()
    {
        assertTrue(InputValidatorUtil.isValidPhoneNumber("1234567890"));
        assertTrue(InputValidatorUtil.isValidPhoneNumber("9876543210"));
        assertTrue(InputValidatorUtil.isValidPhoneNumber("5435743957"));
    }
    @Test
    void isValidPhoneNumber_returnsFalseIfPhoneNumberIsNotValid(){
        assertFalse(InputValidatorUtil.isValidPhoneNumber("123456789"));
        assertFalse(InputValidatorUtil.isValidPhoneNumber("123456789a"));
        assertFalse(InputValidatorUtil.isValidPhoneNumber("asblnkoiun"));
        assertFalse(InputValidatorUtil.isValidPhoneNumber(".........."));
        assertFalse(InputValidatorUtil.isValidPhoneNumber("+11234567890"));
        assertFalse(InputValidatorUtil.isValidPhoneNumber("123-456-7890"));
    }

}