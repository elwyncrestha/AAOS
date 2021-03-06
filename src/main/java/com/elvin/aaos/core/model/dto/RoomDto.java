package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;

@Getter
@Setter
public class RoomDto extends BaseDto<Long> {

    private String name;

    private RoomType roomType;

    private Status status;

    private Long buildingId;

}
