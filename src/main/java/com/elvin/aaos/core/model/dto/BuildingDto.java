package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BuildingDto extends BaseDto<Long> {

    private String name;

    private String description;

    private Set<RoomDto> roomDtos = new HashSet<>();

}
