package com.racanaa.services.account.exception;

/**
 * Exception class to handle parameter constraints
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
public class JsonProcessingException extends RuntimeException {
    public JsonProcessingException() {
        super();
    }

    public JsonProcessingException(String message) {
        super(message);
    }
}
