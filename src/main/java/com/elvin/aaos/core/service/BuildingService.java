package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.BuildingStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BuildingService {

    long countAll();

    BuildingDto save(BuildingDto buildingDto, User user);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<BuildingDto> list();

    BuildingDto getById(long id);

    void delete(long id, User deletedBy);

    BuildingDto update(BuildingDto buildingDto, User modifiedBy);

    long countByStatus(BuildingStatus buildingStatus);

}
