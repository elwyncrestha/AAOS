package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.ExamDto;
import com.elvin.aaos.core.model.dto.ExamModuleDto;
import com.elvin.aaos.core.model.entity.Exam;
import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.ExamMapper;
import com.elvin.aaos.core.model.mapper.ExamModuleMapper;
import com.elvin.aaos.core.model.repository.ExamRepository;
import com.elvin.aaos.core.service.ExamService;
import com.elvin.aaos.core.utility.DateUtils;
import com.elvin.aaos.web.utility.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final ExamRepository examRepository;
    private final ExamModuleMapper examModuleMapper;

    public ExamServiceImpl(
            @Autowired ExamMapper examMapper,
            @Autowired ExamRepository examRepository,
            @Autowired ExamModuleMapper examModuleMapper
    ) {
        this.examMapper = examMapper;
        this.examRepository = examRepository;
        this.examModuleMapper = examModuleMapper;
    }

    @Override
    public ExamDto save(ExamDto examDto, User createdBy) {
        Exam exam = examMapper.mapDtoToEntity(examDto);
        exam.setStartTime(DateUtils.convertDateTime(examDto.getStrStartTime(), DateUtils.HH_mm));
        exam.setEndTime(DateUtils.convertDateTime(examDto.getStrEndTime(), DateUtils.HH_mm));
        Module module = new Module();
        module.setId(examDto.getModuleId());
        exam.setModule(module);
        exam.setStatus(Status.ACTIVE);
        exam.setCreatedBy(createdBy);
        return examMapper.mapEntityToDto(examRepository.save(exam));
    }

    @Override
    public List<ExamModuleDto> list() {
        return examModuleMapper.mapEntitiesToDtos(examRepository.findExamsByStatus(Status.ACTIVE));
    }

    @Override
    public void delete(long id, User deletedBy) {
        Exam exam = examRepository.findExamById(id);
        exam.setName(StringConstants.DELETED_EXAM + exam.getId() + "_" + exam.getName());
        exam.setModule(null);
        exam.setStatus(Status.DELETED);
        exam.setModifiedBy(deletedBy);
        exam.setLastModifiedAt(new Date());
        examRepository.save(exam);
    }

}
