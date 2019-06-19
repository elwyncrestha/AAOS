package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ExamDto extends BaseDto<Long> {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    private String strStartTime;

    private String strEndTime;

    private long moduleId;

    private Status status;

}
