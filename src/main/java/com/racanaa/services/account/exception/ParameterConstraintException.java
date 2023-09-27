package com.racanaa.services.account.exception;

/**
 * Exception class to handle parameter constraints
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
public class ParameterConstraintException extends RuntimeException {
    public ParameterConstraintException() {
        super();
    }

    public ParameterConstraintException(String message) {
        super(message);
    }
}
