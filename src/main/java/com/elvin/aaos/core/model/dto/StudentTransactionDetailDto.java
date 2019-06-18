package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class StudentTransactionDetailDto extends BaseDto<Long> {

    private StudentProfileDto studentProfile;

    private CourseDto course;

    private boolean complete;

    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate = new Date();

    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
