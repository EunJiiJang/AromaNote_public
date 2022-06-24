package com.rf.aromanote.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
