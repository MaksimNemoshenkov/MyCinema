package com.cinema.controllers;

import com.cinema.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    protected ResponseEntity<Object> handleNoDataFoundException(
            NoDataFoundException exception
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleHallNotFoundException(NotFoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
/*
    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }*/
}