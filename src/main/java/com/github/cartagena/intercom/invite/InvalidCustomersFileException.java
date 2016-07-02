package com.github.cartagena.intercom.invite;

import com.google.gson.JsonSyntaxException;

public class InvalidCustomersFileException extends RuntimeException {

    public InvalidCustomersFileException(String message, Exception cause) {
        super(message, cause);
    }

}
