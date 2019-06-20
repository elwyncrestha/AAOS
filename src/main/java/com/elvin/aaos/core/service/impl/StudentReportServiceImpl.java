package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.StudentReportDto;
import com.elvin.aaos.core.model.entity.StudentReport;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.mapper.StudentReportMapper;
import com.elvin.aaos.core.model.repository.StudentReportRepository;
import com.elvin.aaos.core.service.StudentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentReportServiceImpl implements StudentReportService {

    private final StudentReportRepository studentReportRepository;
    private final StudentReportMapper studentReportMapper;

    public StudentReportServiceImpl(
            @Autowired StudentReportRepository studentReportRepository,
            @Autowired StudentReportMapper studentReportMapper
    ) {
        this.studentReportRepository = studentReportRepository;
        this.studentReportMapper = studentReportMapper;
    }

    @Override
    public boolean verifyReportCreation(long studentProfileId, long moduleId) {
        return studentReportRepository.count(studentProfileId, moduleId) > 0;
    }

    @Override
    public StudentReportDto save(StudentReportDto studentReportDto, User createdBy) {
        StudentReport studentReport = studentReportMapper.mapDtoToEntity(studentReportDto);
        studentReport.setCreatedBy(createdBy);
        return studentReportMapper.mapEntityToDto(studentReportRepository.save(studentReport));
    }
}
