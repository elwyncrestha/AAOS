package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.RoomScheduleDto;
import com.elvin.aaos.core.model.entity.RoomSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class RoomScheduleMapper extends BaseMapper<RoomSchedule, RoomScheduleDto> {
}
