package com.allica.demo.exception;

public class BaseRequestException extends RuntimeException{

    public BaseRequestException(String message, String accountNumber){
        super(message);
    }
}
