package com.pragma.plazoletaservice.infrastructure.exceptionhandler;

import com.pragma.plazoletaservice.infrastructure.exception.AlreadyExistsException;
import com.pragma.plazoletaservice.infrastructure.exception.CategoryDoesNotExistException;
import com.pragma.plazoletaservice.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoletaservice.infrastructure.exception.RestaurantDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException(
            AlreadyExistsException ignoredAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(CategoryDoesNotExistException.class)
    public ResponseEntity<Map<String, String>> handleCategoryDoesNotExistException(
            CategoryDoesNotExistException ignoredCategoryDoesNotExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_DOES_NOT_EXIST.getMessage()));
    }

    @ExceptionHandler(RestaurantDoesNotExistException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantDoesNotExistException(
            RestaurantDoesNotExistException ignoredRestaurantDoesNotExistException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.RESTAURANT_DOES_NOT_EXIST.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName + "-error", errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
    
}