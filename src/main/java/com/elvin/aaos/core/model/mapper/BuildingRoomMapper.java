package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.BuildingRoomDto;
import com.elvin.aaos.core.model.entity.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class BuildingRoomMapper extends BaseMapper<Building, BuildingRoomDto> {
}
