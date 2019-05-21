package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoomDto extends BaseDto<Long> {

    private String name;

    private RoomType roomType;

    private BuildingDto building;

    private Set<RoomScheduleDto> roomSchedules = new HashSet<>();

}
