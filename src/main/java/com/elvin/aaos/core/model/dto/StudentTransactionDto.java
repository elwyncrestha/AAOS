package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentTransactionDto extends BaseDto<Long> {

    private StudentProfileDto studentProfile;

    private CourseDto course;

    private boolean isComplete;

    private String remarks;

}
