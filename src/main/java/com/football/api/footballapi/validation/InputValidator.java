package com.football.api.footballapi.validation;

import com.football.api.footballapi.exception.InvalidInputException;

public class InputValidator {

    public static void validateInputParams(String inputParam) {

        if (inputParam == null || inputParam.isEmpty()) {
            return;
        }
        if (!inputParam.chars().allMatch(c -> Character.isAlphabetic(c) || Character.isDigit(c) || Character.isWhitespace(c))) {
            throw new InvalidInputException("The given string is not valid " + inputParam);
        }
    }
}
