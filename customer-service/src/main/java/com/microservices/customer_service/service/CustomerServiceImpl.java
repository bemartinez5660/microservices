package com.microservices.customer_service.service;

import com.microservices.customer_service.repository.CustomerRepository;
import com.microservices.customer_service.repository.entity.Customer;
import com.microservices.customer_service.repository.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomersAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
        if (customerDB != null) {
            return customerDB;
        }
        customer.setState("CREATED");

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerBD = customerRepository.findByNumberID(customer.getNumberID());
        if (customerBD == null) {
            return null;
        }
        customerBD.setState("UPDATED");
        customerBD.setFirstName(customer.getFirstName());
        customerBD.setLastName(customer.getLastName());
        customerBD.setEmail(customer.getEmail());
        customerBD.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(customerBD);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerBD = customerRepository.findByNumberID(customer.getNumberID());
        if (customerBD == null) {
            return null;
        }
        customerBD.setState("DELETED");
        return customerRepository.save(customerBD);

    }

    @Override
    public Customer getCustomer(String id) {
        return customerRepository.findByNumberID(id);
    }
}
