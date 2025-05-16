package com.ratzer.Notification_Service.rest.handler;

import com.ratzer.Notification_Service.api.model.Error;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@ControllerAdvice
public class CentralizedExceptionHandler {
    @ExceptionHandler({DataIntegrityViolationException.class, MethodArgumentTypeMismatchException.class, ServletRequestBindingException.class, IllegalArgumentException.class, ConstraintViolationException.class, HttpMediaTypeNotSupportedException.class, HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Error> handleBadRequestError(Exception e) {
        ServletRequestAttributes attributes = Objects.requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        Error apiError = new Error();
        apiError.setTimestamp(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")));
        apiError.setError(HttpStatus.BAD_REQUEST.toString());
        apiError.setMessage("Invalid request data: " + e.getMessage());
        apiError.setPath(attributes.getRequest().getRequestURI());

        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Error> handleNotFoundError(Exception e) {
        ServletRequestAttributes attributes = Objects.requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        Error apiError = new Error();
        apiError.setTimestamp(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")));
        apiError.setError(HttpStatus.NOT_FOUND.toString());
        apiError.setMessage("Resource not found: " + e.getMessage());
        apiError.setPath(attributes.getRequest().getRequestURI());

        return ResponseEntity.status(404).body(apiError);
    }

    @ExceptionHandler({SQLException.class, DataAccessResourceFailureException.class})
    public ResponseEntity<Error> handleServiceUnavailableError(Exception e) {
        ServletRequestAttributes attributes = Objects.requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        Error apiError = new Error();
        apiError.setTimestamp(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")));
        apiError.setError(HttpStatus.SERVICE_UNAVAILABLE.toString());
        apiError.setMessage("Service is currently unavailable: " + e.getMessage());
        apiError.setPath(attributes.getRequest().getRequestURI());

        return ResponseEntity.status(503).body(apiError);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleUnexpectedError(Exception e) {
        ServletRequestAttributes attributes = Objects.requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        Error apiError = new Error();
        apiError.setTimestamp(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")));
        apiError.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        apiError.setMessage("An unexpected Error occurred: " + e.getMessage());
        apiError.setPath(attributes.getRequest().getRequestURI());

        return ResponseEntity.status(500).body(apiError);
    }
}
