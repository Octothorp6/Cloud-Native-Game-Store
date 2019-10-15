package com.trilogyed.adminapi.exception;

public class ImpossibleDeleteException extends RuntimeException {

    public ImpossibleDeleteException() {
    }

    public ImpossibleDeleteException(String message) {
        super(message);
    }


}
