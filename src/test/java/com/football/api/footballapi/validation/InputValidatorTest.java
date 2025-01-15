package com.football.api.footballapi.validation;

import com.football.api.footballapi.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void validateInputParams() {

        assertDoesNotThrow(()->InputValidator.validateInputParams("testValidInput"));

    }

    @Test
    void validateInputParamsValidCharSpace() {

        assertDoesNotThrow(()->InputValidator.validateInputParams("test ValidInput"));

    }

    @Test
    void validateInputParamsInvalidChar1() {

        assertThrows(InvalidInputException.class, ()->InputValidator.validateInputParams("testVa[lidInput"));

    }

    @Test
    void validateInputParamsInvalidChar2() {

        assertThrows(InvalidInputException.class, ()->InputValidator.validateInputParams("testVa*lidInput"));

    }

    @Test
    void validateInputParamsInvalidChar3() {

        assertThrows(InvalidInputException.class, ()->InputValidator.validateInputParams("testVa%$#lidInput"));

    }


}