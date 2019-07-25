package com.elvin.aaos.core.model.dto;

import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import com.elvin.aaos.core.model.enums.Status;

@Getter
@Setter
public class BatchDto extends BaseDto<Long> {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date formationDate;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
