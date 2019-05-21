package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.entity.User;

import java.util.List;

public interface BuildingService {

    long countAll();

    BuildingDto save(BuildingDto buildingDto, User user);

    List<BuildingDto> list();

    BuildingDto getById(long id);

    void delete(long id);

    BuildingDto update(BuildingDto buildingDto, User modifiedBy);

}
