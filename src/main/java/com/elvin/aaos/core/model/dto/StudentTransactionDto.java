package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentTransactionDto extends BaseDto<Long> {

    private StudentProfileDto studentProfileDto;

    private CourseDto courseDto;

    private boolean isComplete;

    private String remarks;

}
