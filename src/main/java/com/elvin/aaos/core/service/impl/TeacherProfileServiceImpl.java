package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.TeacherProfileDto;
import com.elvin.aaos.core.model.entity.TeacherProfile;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.mapper.TeacherProfileMapper;
import com.elvin.aaos.core.model.repository.TeacherProfileRepository;
import com.elvin.aaos.core.service.TeacherProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;
    private final TeacherProfileMapper teacherProfileMapper;

    public TeacherProfileServiceImpl(
            @Autowired TeacherProfileRepository teacherProfileRepository,
            @Autowired TeacherProfileMapper teacherProfileMapper
    ) {
        this.teacherProfileRepository = teacherProfileRepository;
        this.teacherProfileMapper = teacherProfileMapper;
    }

    @Override
    public TeacherProfileDto getByUserId(long userId) {
        return teacherProfileMapper.mapEntityToDto(teacherProfileRepository.findByUserId(userId));
    }

    @Override
    public TeacherProfileDto save(TeacherProfileDto teacherProfileDto, User createdOrModifiedBy) {
        TeacherProfile teacherProfile = teacherProfileMapper.mapDtoToEntity(teacherProfileDto);
        if (teacherProfileDto.getId() == null) {
            teacherProfile.setCreatedBy(createdOrModifiedBy);
        } else {
            TeacherProfile oldTeacherProfile  = teacherProfileRepository.findTeacherProfileById(teacherProfileDto.getId());
            teacherProfile.setCreatedBy(oldTeacherProfile.getCreatedBy());
            teacherProfile.setCreatedAt(oldTeacherProfile.getCreatedAt());
            teacherProfile.setModifiedBy(createdOrModifiedBy);
        }

        return teacherProfileMapper.mapEntityToDto(teacherProfileRepository.save(teacherProfile));
    }
}
