package com.microservices.customer_service.service;


import com.microservices.customer_service.repository.entity.Customer;
import com.microservices.customer_service.repository.entity.Region;

import java.util.List;

public interface CustomerService {
    public List<Customer> findCustomersAll();
    public List<Customer> findCustomersByRegion(Region region);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public Customer getCustomer(String id);
}
