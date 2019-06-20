package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class StudentReportDto extends BaseDto<Long> {

    private ModuleDto module;

    private StudentProfileDto studentProfile;

    private double marksObtained;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
