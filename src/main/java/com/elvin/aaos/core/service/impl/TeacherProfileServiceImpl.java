package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.TeacherDto;
import com.elvin.aaos.core.model.dto.TeacherProfileDto;
import com.elvin.aaos.core.model.entity.TeacherProfile;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.mapper.TeacherMapper;
import com.elvin.aaos.core.model.mapper.TeacherProfileMapper;
import com.elvin.aaos.core.model.repository.TeacherProfileRepository;
import com.elvin.aaos.core.service.TeacherProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;
    private final TeacherProfileMapper teacherProfileMapper;
    private final TeacherMapper teacherMapper;

    public TeacherProfileServiceImpl(
            @Autowired TeacherProfileRepository teacherProfileRepository,
            @Autowired TeacherProfileMapper teacherProfileMapper,
            @Autowired TeacherMapper teacherMapper
    ) {
        this.teacherProfileRepository = teacherProfileRepository;
        this.teacherProfileMapper = teacherProfileMapper;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherProfileDto getByUserId(long userId) {
        return teacherProfileMapper.mapEntityToDto(teacherProfileRepository.findByUserId(userId));
    }

    @Override
    public TeacherDto save(TeacherProfileDto teacherProfileDto, User createdOrModifiedBy) {
        TeacherProfile teacherProfile = teacherProfileMapper.mapDtoToEntity(teacherProfileDto);
        if (teacherProfileDto.getId() == null) {
            teacherProfile.setCreatedBy(createdOrModifiedBy);
        } else {
            TeacherProfile oldTeacherProfile  = teacherProfileRepository.findTeacherProfileById(teacherProfileDto.getId());
            teacherProfile.setCreatedBy(oldTeacherProfile.getCreatedBy());
            teacherProfile.setCreatedAt(oldTeacherProfile.getCreatedAt());
            teacherProfile.setModifiedBy(createdOrModifiedBy);
        }

        return teacherMapper.mapEntityToDto(teacherProfileRepository.save(teacherProfile));
    }

    @Override
    public List<TeacherProfileDto> list() {
        return teacherProfileMapper.mapEntitiesToDtos(teacherProfileRepository.findAll());
    }

    @Override
    public long countModuleAssigned() {
        return teacherProfileRepository.countTeacherWithModule();
    }

    @Override
    public long countModuleUnassigned() {
        return teacherProfileRepository.countTeacherWithoutModule();
    }

    @Override
    public TeacherProfileDto getById(long id) {
        return teacherProfileMapper.mapEntityToDto(teacherProfileRepository.findTeacherProfileById(id));
    }
}
