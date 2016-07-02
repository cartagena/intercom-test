package com.github.cartagena.intercom.invite.customer;

public class InvalidCustomersFileException extends RuntimeException {

    public InvalidCustomersFileException(String message, Exception cause) {
        super(message, cause);
    }

}
