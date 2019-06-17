package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentProfileService {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    StudentProfileDto getByUserId(long userId);

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    StudentProfileDto save(StudentProfileDto studentProfileDto, User createdOrModifiedBy);

    boolean hasAssociatedBatch(long batchId);

    long countBatchAssigned();

    long countBatchUnassigned();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<StudentProfileDto> list();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    StudentProfileDto getById(long id);

}
