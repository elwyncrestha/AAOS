package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentReportDto extends BaseDto<Long> {

    private ModuleDto module;

    private StudentProfileDto studentProfile;

    private double marksObtained;

}
