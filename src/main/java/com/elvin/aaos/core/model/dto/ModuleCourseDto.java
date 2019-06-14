package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class ModuleCourseDto extends BaseDto<Long> {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private CourseDto course;

}
