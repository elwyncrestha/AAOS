package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BuildingDto extends BaseDto<Long> {

    private String name;

    private String description;

    private Status status;

    private Set<RoomDto> roomDtos = new HashSet<>();

}
