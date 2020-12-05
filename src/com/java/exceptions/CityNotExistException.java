package com.java.exceptions;

public class CityNotExistException extends Exception {

    public CityNotExistException() {
        super("The city is not in the database");
    }
}
