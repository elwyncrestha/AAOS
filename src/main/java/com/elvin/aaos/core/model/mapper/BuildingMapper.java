package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.dto.BuildingRoomDto;
import com.elvin.aaos.core.model.dto.RoomBuildingDto;
import com.elvin.aaos.core.model.entity.Building;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class BuildingMapper extends BaseMapper<Building, BuildingDto> {

    public abstract BuildingRoomDto mapBuildingToBuildingRoomDto(Building entity);

    public abstract Building mapBuildingRoomDtoToBuilding(BuildingRoomDto dto);

}
