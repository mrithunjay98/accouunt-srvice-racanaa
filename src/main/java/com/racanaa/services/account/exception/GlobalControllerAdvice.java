package com.racanaa.services.account.exception;

import com.racanaa.services.account.constant.ApiConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Global controller advice to handle exceptions
 *
 * @author Manoahr
 * @see ControllerAdvice
 * @since 20/Sep/2023
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    private Environment env;

    /**
     * Exception handler for MethodArgumentNotValidException
     *
     * @param e exception object
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse apiErrorResponse = new ErrorResponse(status,
                ApiConstant.VALIDATION_FAILED_ERROR_MESSAGE,
                stackTrace(e), errors);
        return ResponseEntity
                       .status(status)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for DataValidationException
     *
     * @param e exception object
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataValidationException.class})
    public ResponseEntity<ErrorResponse> handleDataValidationException(
            DataValidationException e, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e), e.getErrors());
        return ResponseEntity
                       .status(status)
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(apiErrorResponse);
    }


    /**
     * Exception handler for JsonProcessingException
     *
     * @param e exception object
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({JsonProcessingException.class})
    public ResponseEntity<ErrorResponse> handleJsonProcessingException(
            JsonProcessingException e, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e));
        return ResponseEntity
                       .status(status)
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for IllegalArgumentException
     *
     * @param e exception object
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e));
        return ResponseEntity
                       .status(status)
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for HttpMessageNotReadableException
     *
     * @param e exception object
     * @return
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e));
        return ResponseEntity
                       .status(status)
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for NoHandlerFoundException
     *
     * @param e exception object
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
            NoHandlerFoundException e, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage());
        return ResponseEntity
                       .status(status)
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for CustomRuntimeException
     *
     * @param e exception object
     * @return
     */
    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleCustomRuntimeException(Exception e) {
        CustomRuntimeException ex = (CustomRuntimeException) e;
        HttpStatus status = ex.getStatus();
        ErrorResponse apiErrorResponse = new ErrorResponse(status, ex.getMessage(), stackTrace(ex));
        return ResponseEntity
                       .status(status)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for DataNotFoundException
     *
     * @param e exception object
     * @return
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e));
        return ResponseEntity
                       .status(status)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for ResourceAlreadyExistException
     *
     * @param e exception object
     * @return
     */
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistException(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e));
        return ResponseEntity
                       .status(status)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for ParameterConstraintException
     *
     * @param e exception object
     * @return
     */
    @ExceptionHandler(ParameterConstraintException.class)
    public ResponseEntity<ErrorResponse> handleParameterConstraintException(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage());
        return ResponseEntity
                       .status(status)
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(apiErrorResponse);
    }

    /**
     * Exception handler for NullPointerException
     *
     * @param e exception object
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e));
        return ResponseEntity
                       .status(status)
                       .body(apiErrorResponse);
    }

    /**
     * This is the generic catch all exception handler.
     * Generic fallback to 500 internal server error
     *
     * @param e exception object
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse apiErrorResponse = new ErrorResponse(status, e.getMessage(), stackTrace(e));
        return ResponseEntity
                       .status(status)
                       .body(apiErrorResponse);
    }

    /**
     * Returns the stackTrace text
     *
     * @param e exception object
     * @return
     */
    private String stackTrace(Exception e) {
        String stackTraceProp = env.getProperty(ApiConstant.STACK_TRACE_PROPERTY);
        if (!ApiConstant.STACK_TRACE_ALWAYS.equalsIgnoreCase(stackTraceProp)) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}