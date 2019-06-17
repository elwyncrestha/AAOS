package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.TeacherDto;
import com.elvin.aaos.core.model.dto.TeacherProfileDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherProfileService {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    TeacherProfileDto getByUserId(long userId);

    TeacherDto save(TeacherProfileDto teacherProfileDto, User createdOrModifiedBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<TeacherProfileDto> list();

    long countModuleAssigned();

    long countModuleUnassigned();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    TeacherProfileDto getById(long id);

}
