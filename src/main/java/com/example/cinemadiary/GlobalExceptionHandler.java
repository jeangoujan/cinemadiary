package com.example.cinemadiary;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
