package com.microservices.customer_service.repository;

import com.microservices.customer_service.repository.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
    public Region findById(long id);
}
