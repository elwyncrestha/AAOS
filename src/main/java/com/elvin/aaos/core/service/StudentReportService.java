package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.StudentReportDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentReportService {

    boolean verifyReportCreation(long studentProfileId, long moduleId);

    StudentReportDto save(StudentReportDto studentReportDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<StudentReportDto> listByStudentId(long studentProfileId);

}
