package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.entity.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class BuildingMapper extends BaseMapper<Building, BuildingDto> {
}
