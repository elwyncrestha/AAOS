package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.StudentReportDto;
import com.elvin.aaos.core.model.entity.StudentReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class StudentReportMapper extends BaseMapper<StudentReport, StudentReportDto> {
}
