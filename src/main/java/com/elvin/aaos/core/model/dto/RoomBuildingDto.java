package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBuildingDto {

    private String name;

    private RoomType roomType;

    private Status status;

    private BuildingDto building;

}
