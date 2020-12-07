package com.belo4ya.exceptions;

public class ServiceNotExistException extends Exception {

    public ServiceNotExistException() {
        super("The weather service is not in the database");
    }
}
