package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.ExamDto;
import com.elvin.aaos.core.model.entity.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class ExamMapper extends BaseMapper<Exam, ExamDto> {
}
