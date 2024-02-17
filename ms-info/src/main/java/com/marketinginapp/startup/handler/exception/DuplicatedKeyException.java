package com.marketinginapp.startup.handler.exception;

public class DuplicatedKeyException extends RuntimeException {
    public DuplicatedKeyException(String message) {
        super(message);
    }
}
