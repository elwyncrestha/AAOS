package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.BuildingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingDto extends BaseDto<Long> {

    private String name;

    private String description;

    private BuildingStatus status;

}
