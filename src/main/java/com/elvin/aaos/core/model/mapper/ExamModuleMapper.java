package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.ExamModuleDto;
import com.elvin.aaos.core.model.entity.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class ExamModuleMapper extends BaseMapper<Exam, ExamModuleDto> {
}
