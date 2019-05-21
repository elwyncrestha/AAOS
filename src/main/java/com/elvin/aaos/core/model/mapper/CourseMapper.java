package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.CourseDto;
import com.elvin.aaos.core.model.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class CourseMapper extends BaseMapper<Course, CourseDto> {
}
