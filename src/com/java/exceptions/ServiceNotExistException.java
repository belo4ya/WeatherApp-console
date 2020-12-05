package com.java.exceptions;

public class ServiceNotExistException extends Exception {

    public ServiceNotExistException() {
        super("The weather service is not in the database");
    }
}
