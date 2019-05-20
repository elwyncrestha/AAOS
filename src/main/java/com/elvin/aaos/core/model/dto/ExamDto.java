package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ExamDto extends BaseDto<Long> {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date end;

    private ModuleDto moduleDto;

}
