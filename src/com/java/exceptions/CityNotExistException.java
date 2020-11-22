package com.java.exceptions;

public class CityNotExistException extends Exception {

    public CityNotExistException() {
        super("Города нет в базе данных");
    }
}
