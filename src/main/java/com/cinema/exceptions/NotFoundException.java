package com.cinema.exceptions;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such")
public class NotFoundException extends RuntimeException{
    public NotFoundException (String entityName, Long id){
        super(entityName + " with id: " + id + " not found");
    }
}
