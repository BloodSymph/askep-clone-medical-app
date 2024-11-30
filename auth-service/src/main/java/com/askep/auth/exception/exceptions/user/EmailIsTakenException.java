package com.askep.auth.exception.exceptions.user;

public class EmailIsTakenException extends RuntimeException{

    public EmailIsTakenException(String message) {
        super(message);
    }

}
