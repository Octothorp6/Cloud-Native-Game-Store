package com.trilogyed.adminapi.exception;

public class NullListReturnException extends RuntimeException {

    public NullListReturnException() {
    }

    public NullListReturnException(String message) {
        super(message);
    }

}
