package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.entity.User;

public interface BuildingService {

    long countAll();

    BuildingDto save(BuildingDto buildingDto, User user);

}
