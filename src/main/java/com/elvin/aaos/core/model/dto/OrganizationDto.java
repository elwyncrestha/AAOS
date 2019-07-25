package com.elvin.aaos.core.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto extends BaseDto<Long> {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date establishment;

    private String description;

}
