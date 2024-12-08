package com.microservices.customer_service.service;

import com.microservices.customer_service.repository.RegionRepository;
import com.microservices.customer_service.repository.entity.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(long id) {
        return regionRepository.findById(id);
    }

    @Override
    public Region createRegion(Region region) {

        return regionRepository.save(region);
    }


}
