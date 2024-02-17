package com.marketinginapp.startup.handler.exception;

public class AccessForbiddenException extends RuntimeException{
    public AccessForbiddenException(String message) {
        super(message);
    }
}
