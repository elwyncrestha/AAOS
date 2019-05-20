package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingDto extends BaseDto<Long> {

    private String name;

    private String description;

}
