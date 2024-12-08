package com.microservices.customer_service.service;

import com.microservices.customer_service.repository.entity.Region;

import java.util.List;

public interface RegionService {
    public List<Region> getAllRegions();
    public Region getRegionById(long id);
    public Region createRegion(Region region);
}
