package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.BatchCourseDto;
import com.elvin.aaos.core.model.entity.Batch;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class BatchCourseMapper extends BaseMapper<Batch, BatchCourseDto> {
}
