package com.elvin.aaos.core.model.mapper;


import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.model.entity.Batch;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class BatchMapper extends BaseMapper<Batch, BatchDto> {
}
