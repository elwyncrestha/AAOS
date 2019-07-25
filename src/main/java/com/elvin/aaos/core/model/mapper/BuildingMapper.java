package com.elvin.aaos.core.model.mapper;

import org.mapstruct.Mapper;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.entity.Building;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class BuildingMapper extends BaseMapper<Building, BuildingDto> {

}
