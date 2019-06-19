package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.ExamDto;
import com.elvin.aaos.core.model.dto.ExamModuleDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamService {

    ExamDto save(ExamDto examDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true ,noRollbackFor = Exception.class)
    List<ExamModuleDto> list();

    void delete(long id, User deletedBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true ,noRollbackFor = Exception.class)
    ExamModuleDto getById(long id);

    ExamDto update(ExamDto examDto, User modifiedBy);

}
