package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.entity.Module;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class ModuleMapper extends BaseMapper<Module, ModuleDto> {
}
