package com.trilogyed.adminapi.exception;

public class NullObjectReturnException extends RuntimeException {

    public NullObjectReturnException() {
    }

    public NullObjectReturnException(String message) {
        super(message);
    }

}
