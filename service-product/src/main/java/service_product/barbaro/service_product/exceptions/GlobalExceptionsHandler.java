package service_product.barbaro.service_product.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(ResponseStatusException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("timestamp", String.valueOf(System.currentTimeMillis()));
        body.put("status", String.valueOf(ex.getStatusCode().value()));
        body.put("error", ex.getMessage());
        body.put("message", ex.getReason());
        body.put("path", ex.getStackTrace()[0].toString());
        return ResponseEntity.badRequest().body(body);
    }
}
