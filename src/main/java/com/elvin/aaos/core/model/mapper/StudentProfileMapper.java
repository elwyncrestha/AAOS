package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.core.model.entity.StudentProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class StudentProfileMapper extends BaseMapper<StudentProfile, StudentProfileDto> {
}
