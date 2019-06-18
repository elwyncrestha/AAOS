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
public class StudentTransactionDto extends BaseDto<Long> {

    private long studentProfileId;

    private long courseId;

    private boolean complete;

    private String remarks;

    @Temporal(TemporalType.DATE)
    private Date transactionDate = new Date();

    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
