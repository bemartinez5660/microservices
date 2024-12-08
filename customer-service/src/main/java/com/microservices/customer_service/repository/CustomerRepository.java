package com.microservices.customer_service.repository;

import com.microservices.customer_service.repository.entity.Customer;
import com.microservices.customer_service.repository.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Customer findByNumberID(String numberId);
    public Customer findById(long id);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegion(Region region);
}
