package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.StudentReportDto;
import com.elvin.aaos.core.model.entity.User;

public interface StudentReportService {

    boolean verifyReportCreation(long studentProfileId, long moduleId);

    StudentReportDto save(StudentReportDto studentReportDto, User createdBy);

}
