package com.elvin.aaos.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto extends BaseDto<Long>{

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date establishment;

    private String description;

}
