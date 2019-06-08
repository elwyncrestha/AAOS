package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface StudentProfileService {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    StudentProfileDto getByUserId(long userId);

    StudentProfileDto save(StudentProfileDto studentProfileDto, User createdOrModifiedBy);

}
