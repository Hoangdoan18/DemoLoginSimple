package com.example.demologin.exception;

public class DuplicateRecordException extends RuntimeException {
    public DuplicateRecordException(String message){
        super(message);
    }
}
