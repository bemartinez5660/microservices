package com.microservices.customer_service.controller;

import com.microservices.customer_service.repository.entity.Region;
import com.microservices.customer_service.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @PostMapping
    public Region createRegion(@RequestBody Region region) {
        return regionService.createRegion(region);
    }

    @GetMapping("/{id}")
    public Region getRegion(@PathVariable long id){
        return regionService.getRegionById(id);
    }

    @GetMapping
    public List<Region> getAllRegions(){
        return regionService.getAllRegions();
    }
}
