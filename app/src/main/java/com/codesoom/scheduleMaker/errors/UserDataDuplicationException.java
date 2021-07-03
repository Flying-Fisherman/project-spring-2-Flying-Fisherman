package com.codesoom.scheduleMaker.errors;

public class UserDataDuplicationException extends RuntimeException{
    public UserDataDuplicationException() {
        super("User data is already existed");
    }
}
