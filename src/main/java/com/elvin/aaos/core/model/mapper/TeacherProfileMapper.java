package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.TeacherProfileDto;
import com.elvin.aaos.core.model.entity.TeacherProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class TeacherProfileMapper extends BaseMapper<TeacherProfile, TeacherProfileDto> {
}
