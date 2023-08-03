package com.ismailulasunal.twitterclone.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public EmailAlreadyTakenException() {
        super("The e mail provided is already taken");
    }
}
