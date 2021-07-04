package com.codesoom.scheduleMaker.errors;

public class LoginFailException extends RuntimeException{
    public LoginFailException() {
        super("Login is failed - Your ID is not exist or Wrong password");
    }
}
