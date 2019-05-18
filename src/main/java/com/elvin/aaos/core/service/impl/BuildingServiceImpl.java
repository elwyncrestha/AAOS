package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.repository.BuildingRepository;
import com.elvin.aaos.core.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Override
    public long countAll() {
        return buildingRepository.countAll();
    }
}
