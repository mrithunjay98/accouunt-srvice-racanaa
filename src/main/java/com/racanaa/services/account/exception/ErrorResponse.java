package com.racanaa.services.account.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.*;

/**
 * Global custom error response
 *
 * @author Manoahr
 * @since 20/Sep/2023
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss:SSSXXX z")
    private Date timestamp;

    private int code;

    private String status;

    private String message;

    private String stackTrace;

    private List<ValidationError> errors;

    public ErrorResponse() {
        id = UUID.randomUUID().toString();
        timestamp = new Date();
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.message = message;
    }

    public ErrorResponse(HttpStatus httpStatus, String message, String stackTrace) {
        this(httpStatus, message);
        this.stackTrace = stackTrace;
    }

    /**
     * Constructor to create ErrorResponse. This is typically used input data validation.
     *
     * @param httpStatus
     * @param message
     * @param stackTrace
     * @param errorsMap  Map of error message
     */
    public ErrorResponse(HttpStatus httpStatus, String message, String stackTrace, Map<String, String> errorsMap) {
        this(httpStatus, message, stackTrace);
        if (errorsMap != null && errorsMap.size() > 0) {
            List<ValidationError> vErrors = new ArrayList<>();
            errorsMap.forEach((key, value) -> {
                ValidationError ve = new ValidationError(key, value);
                vErrors.add(ve);
            });
            this.errors = vErrors;
        }
    }

    /**
     * A custom class for storing validation errors
     */
    @Data
    class ValidationError {
        String field;
        String message;

        ValidationError() {
            super();
        }

        ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }

    /**
     * Used to create a JSON string from the filter.
     *
     * @return String json string representation of exception object
     * @throws JsonProcessingException
     */
    public String convertToJson() throws JsonProcessingException {
        if (this == null) return null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(this);
    }
}
