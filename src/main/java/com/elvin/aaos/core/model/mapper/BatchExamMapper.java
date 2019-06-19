package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.BatchExamDto;
import com.elvin.aaos.core.model.entity.Batch;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class BatchExamMapper extends BaseMapper<Batch, BatchExamDto> {
}
