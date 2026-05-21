package com.example.cinemadiary;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException exception){
        List<String> messages = exception.getBindingResult().getFieldErrors()
        .stream()
        .map(fieldError -> fieldError.getDefaultMessage())
        .toList();

        ApiErrorResponse response = new ApiErrorResponse(400,"VALIDATION_ERROR", messages);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(ResponseStatusException exception){
        int statusCode = exception.getStatusCode().value();

        ApiErrorResponse response = new ApiErrorResponse(
            statusCode,
            exception.getStatusCode().toString(),
            List.of(exception.getReason())
        );

        return ResponseEntity
            .status(exception.getStatusCode())
            .body(response);
    }


}
