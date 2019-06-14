package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.ModuleCourseDto;
import com.elvin.aaos.core.model.entity.Module;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class ModuleCourseMapper extends BaseMapper<Module, ModuleCourseDto> {
}
