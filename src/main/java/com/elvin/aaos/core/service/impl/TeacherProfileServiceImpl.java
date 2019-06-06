package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.TeacherProfileDto;
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
}
