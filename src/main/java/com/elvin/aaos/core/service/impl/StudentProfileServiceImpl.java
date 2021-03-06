package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.core.model.entity.StudentProfile;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.mapper.StudentProfileMapper;
import com.elvin.aaos.core.model.repository.StudentProfileRepository;
import com.elvin.aaos.core.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final StudentProfileMapper studentProfileMapper;

    public StudentProfileServiceImpl(
            @Autowired StudentProfileRepository studentProfileRepository,
            @Autowired StudentProfileMapper studentProfileMapper
    ) {
        this.studentProfileRepository = studentProfileRepository;
        this.studentProfileMapper = studentProfileMapper;
    }

    @Override
    public StudentProfileDto getByUserId(long userId) {
        return studentProfileMapper.mapEntityToDto(studentProfileRepository.findByUserId(userId));
    }

    @Override
    public StudentProfileDto save(StudentProfileDto studentProfileDto, User createdOrModifiedBy) {
        StudentProfile studentProfile = studentProfileMapper.mapDtoToEntity(studentProfileDto);
        if (studentProfileDto.getId() == null) {
            studentProfile.setCreatedBy(createdOrModifiedBy);
        } else {
            StudentProfile oldStudentProfile = studentProfileRepository.findStudentProfileById(studentProfileDto.getId());
            studentProfile.setCreatedBy(oldStudentProfile.getCreatedBy());
            studentProfile.setCreatedAt(oldStudentProfile.getCreatedAt());
            studentProfile.setModifiedBy(createdOrModifiedBy);
        }

        return studentProfileMapper.mapEntityToDto(studentProfileRepository.save(studentProfile));
    }

    @Override
    public boolean hasAssociatedBatch(long batchId) {
        return studentProfileRepository.countAllByBatchId(batchId) > 0;
    }

    @Override
    public long countBatchAssigned() {
        return studentProfileRepository.countStudentWithBatch();
    }

    @Override
    public long countBatchUnassigned() {
        return studentProfileRepository.countStudentWithoutBatch();
    }

    @Override
    public List<StudentProfileDto> list() {
        return studentProfileMapper.mapEntitiesToDtos(studentProfileRepository.findAll());
    }

    @Override
    public StudentProfileDto getById(long id) {
        return studentProfileMapper.mapEntityToDto(studentProfileRepository.findStudentProfileById(id));
    }

    @Override
    public List<StudentProfileDto> listByBatch(long batchId) {
        return studentProfileMapper.mapEntitiesToDtos(studentProfileRepository.findAllByBatchId(batchId));
    }

}
