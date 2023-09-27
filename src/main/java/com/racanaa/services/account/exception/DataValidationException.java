

package com.racanaa.services.account.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Exception class to DataValidationException
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataValidationException extends RuntimeException {
    private HttpStatus status = null;
    private Map<String, String> errors = null;

    public DataValidationException() {
        super();
    }

    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(HttpStatus status, String message) {
        this(message);
        this.status = status;
    }

    public DataValidationException(HttpStatus status, String message, Map<String, String> errors) {
        this(status, message);
        this.errors = errors;
    }
}
