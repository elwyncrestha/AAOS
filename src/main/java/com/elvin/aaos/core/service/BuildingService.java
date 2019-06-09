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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    BuildingDto getById(long id);

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    void delete(long id, User deletedBy);

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    BuildingDto update(BuildingDto buildingDto, User modifiedBy);

    long countByStatus(BuildingStatus buildingStatus);

}
