package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.ModuleCourseDto;
import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ModuleService {

    boolean hasAssociatedCourse(long moduleId);

    long countModuleByStatus(Status status);

    ModuleDto save(ModuleDto moduleDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<ModuleCourseDto> list();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    ModuleCourseDto getById(long id);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    ModuleDto getModuleById(long id);

    void delete(long id, User deletedBy);

    ModuleDto update(ModuleDto moduleDto, User modifiedBy);

}
