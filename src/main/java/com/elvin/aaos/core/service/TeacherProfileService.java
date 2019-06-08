package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.TeacherProfileDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherProfileService {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    TeacherProfileDto getByUserId(long userId);

    TeacherProfileDto save(TeacherProfileDto teacherProfileDto, User createdOrModifiedBy);

}
