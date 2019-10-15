package com.trilogyed.adminapi.exception;

public class CantUpdateObjectException extends RuntimeException {


    public CantUpdateObjectException() {
    }

    public CantUpdateObjectException(String message) {
        super(message);
    }
}
