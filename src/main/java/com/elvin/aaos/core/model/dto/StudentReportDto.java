package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentReportDto extends BaseDto<Long> {

    private ModuleDto moduleDto;

    private StudentProfileDto studentProfileDto;

    private double marksObtained;

}
