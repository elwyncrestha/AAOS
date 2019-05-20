package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StudentProfileDto extends BaseDto<Long> {

    private UserDto userDto;

    private BatchDto batchDto;

    private Set<StudentReportDto> studentReportDtos;

    private Set<StudentTransactionDto> studentTransactionDtos;

}
