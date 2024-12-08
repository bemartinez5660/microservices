package com.microservices.customer_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.customer_service.exceptions.ErrorMessage;
import com.microservices.customer_service.repository.entity.Customer;
import com.microservices.customer_service.repository.entity.Region;
import com.microservices.customer_service.service.CustomerService;
import com.microservices.customer_service.service.RegionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RegionService regionService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            log.info("Customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<Customer>> getAllCustomersByRegion(@PathVariable Long regionId) {
        Region regionDB = regionService.getRegionById(regionId);
        if (regionDB == null) {
            log.error("Region with id " + regionId + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerService.findCustomersByRegion(regionDB));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findCustomersAll());
    }

    // implementar el binding results para cuando fallan las validaciones muestre un mensaje y saber donde es
    // la cosa
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage(bindingResult));
        }

        Customer newCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }

    private String formatMessage(BindingResult bindingResult) {
        List<Map<String, String>> errors = bindingResult.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(err.getField(), err.getDefaultMessage());
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
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Customer customerDB = customerService.getCustomer(id);
        if (customerDB == null) {
            log.error("Customer with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String id) {
        Customer customerDB = customerService.getCustomer(id);
        if (customerDB == null) {
            log.error("Customer with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerService.deleteCustomer(customerDB));
    }

}
