package com.racanaa.services.account.exception;

/**
 * Exception class to handle custom data not found
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}