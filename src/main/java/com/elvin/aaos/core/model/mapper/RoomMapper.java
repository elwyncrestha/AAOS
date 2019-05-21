package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.RoomDto;
import com.elvin.aaos.core.model.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class RoomMapper extends BaseMapper<Room, RoomDto> {
}
