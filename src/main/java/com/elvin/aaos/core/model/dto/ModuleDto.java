package com.elvin.aaos.core.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

import com.elvin.aaos.core.model.enums.Status;

@Getter
@Setter
public class ModuleDto extends BaseDto<Long> {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private Long courseId;

}
