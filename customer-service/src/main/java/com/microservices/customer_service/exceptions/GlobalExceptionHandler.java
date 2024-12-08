package com.microservices.customer_service.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(ResponseStatusException ex, HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", System.currentTimeMillis() + "");
        response.put("status", String.valueOf(ex.getStatusCode().value()));
        response.put("message", ex.getReason());
        response.put("error", ex.getMessage());
        response.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

}
