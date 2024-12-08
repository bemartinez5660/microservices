package microservices.shopping_service.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleException(ResponseStatusException ex, HttpServletRequest request) {
        Map<String,String> response = new HashMap<>();
        response.put("message", ex.getReason());
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        response.put("error", String.valueOf(ex.getStatusCode().value()));
        response.put("status", String.valueOf(ex.getStatusCode().value()));
        response.put("path", request.getRequestURI());
        return ResponseEntity.badRequest().body(response);
    }
}
