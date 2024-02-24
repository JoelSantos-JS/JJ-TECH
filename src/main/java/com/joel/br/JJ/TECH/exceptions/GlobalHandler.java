package com.joel.br.JJ.TECH.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpRequest;
import java.time.Instant;

@RestControllerAdvice
public class GlobalHandler {





    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<FieldMessage> categoryNotFound(HttpServletRequest request , CategoryNotFoundException e) {
        FieldMessage fieldMessage = new FieldMessage();

        fieldMessage.setMessage(e.getMessage());
        fieldMessage.setTimestamp(Instant.now());
        fieldMessage.setPath(request.getRequestURI());
        fieldMessage.setStatus(HttpStatus.NOT_FOUND.toString());
        fieldMessage.setError("Category Not Found");
        return ResponseEntity.ok().body(fieldMessage);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<FieldMessage> categoryNotFound(HttpServletRequest request , ProductNotFoundException e) {
        FieldMessage fieldMessage = new FieldMessage();

        fieldMessage.setMessage(e.getMessage());
        fieldMessage.setTimestamp(Instant.now());
        fieldMessage.setPath(request.getRequestURI());
        fieldMessage.setStatus(HttpStatus.NOT_FOUND.toString());
        fieldMessage.setError("Product Not Found");
        return ResponseEntity.ok().body(fieldMessage);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<FieldMessage> categoryNotFound(HttpServletRequest request , UserNotFoundException e) {
        FieldMessage fieldMessage = new FieldMessage();

        fieldMessage.setMessage(e.getMessage());
        fieldMessage.setTimestamp(Instant.now());
        fieldMessage.setPath(request.getRequestURI());
        fieldMessage.setStatus(HttpStatus.NOT_FOUND.toString());
        fieldMessage.setError("User Not Found");
        return ResponseEntity.ok().body(fieldMessage);

    }
}
