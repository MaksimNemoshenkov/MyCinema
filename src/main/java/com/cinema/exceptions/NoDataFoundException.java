package com.cinema.exceptions;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String entitiesName) {
        super("Data "+ entitiesName + " not found");
    }
}
