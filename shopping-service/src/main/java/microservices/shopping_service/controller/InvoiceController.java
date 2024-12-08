package microservices.shopping_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.shopping_service.entity.Invoice;
import microservices.shopping_service.exceptions.ErrorMessage;
import microservices.shopping_service.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage(result));
        }

        return ResponseEntity.ok(invoiceService.createInvoice(invoice));
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(error.getField(), error.getDefaultMessage());
                    return map;
                }).toList();
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return errorMessage.toString();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice, @PathVariable long id) {
        invoice.setId(id);
        Invoice newInvoice = invoiceService.updateInvoice(invoice);
        if (newInvoice == null) {
            log.error("Invoice with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(newInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable long id){
        Invoice invoiceDB = invoiceService.getInvoice(id);
        if (invoiceDB == null) {
            log.error("Invoice with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invoiceService.deleteInvoice(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable long id) {

        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getInvoices(@RequestParam(required = false) String customerId ) {
        if (customerId != null) {
            return ResponseEntity.ok(invoiceService.getInvoicesByCustomerId(customerId));
        }
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

}
