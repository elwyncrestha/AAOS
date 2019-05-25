package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.RoomBuildingDto;
import com.elvin.aaos.core.model.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class RoomBuildingMapper extends BaseMapper<Room, RoomBuildingDto> {
}
