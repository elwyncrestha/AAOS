package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.BuildingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BuildingDto extends BaseDto<Long> {

    private String name;

    private String description;

    private BuildingStatus status;

    private Set<RoomDto> rooms = new HashSet<>();

}
