package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.RoomScheduleDetailDto;
import com.elvin.aaos.core.model.entity.RoomSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class RoomScheduleDetailMapper extends BaseMapper<RoomSchedule, RoomScheduleDetailDto> {
}
