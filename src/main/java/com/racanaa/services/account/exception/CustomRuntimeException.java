package com.racanaa.services.account.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Exception class to handle Runtime exceptions
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomRuntimeException extends RuntimeException {
    private HttpStatus status = null;
    private Object data = null;

    public CustomRuntimeException() {
        super();
    }

    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(HttpStatus status, String message) {
        this(message);
        this.status = status;
    }

    public CustomRuntimeException(HttpStatus status, String message, Object data) {
        this(status, message);
        this.data = data;
    }
}
