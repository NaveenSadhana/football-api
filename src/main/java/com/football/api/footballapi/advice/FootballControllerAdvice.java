package com.football.api.footballapi.advice;

import com.football.api.footballapi.exception.InvalidInputException;
import com.football.api.footballapi.exception.NoResultFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class FootballControllerAdvice {

    @ExceptionHandler(value = {HttpClientErrorException.BadRequest.class})
    public ResponseEntity<Map<String, Object>> badRequestException(Exception ex, WebRequest request) {
        log.error("Error occurred", ex);
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", ex.getMessage());
        response.put("statusCode", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoResultFoundException.class})
    public ResponseEntity<Map<String, Object>> NotFoundException(Exception ex, WebRequest request) {
        log.error("Error occurred", ex);
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", ex.getMessage());
        response.put("statusCode", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<Map<String, Object>> genericException(Exception ex, WebRequest request) {
        log.error("Error occurred", ex);
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", ex.getMessage());
        response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Map<String, Object>> invalidInputException(Exception ex, WebRequest request) {
        log.error("Error occurred", ex);
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", ex.getMessage());
        response.put("statusCode", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
