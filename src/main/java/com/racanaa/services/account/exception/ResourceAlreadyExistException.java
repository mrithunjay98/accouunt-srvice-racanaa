package com.racanaa.services.account.exception;

/**
 * Exception class to handle resource already exist exception
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException() {
        super();
    }

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}