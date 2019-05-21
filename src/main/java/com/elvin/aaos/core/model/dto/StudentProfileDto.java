package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StudentProfileDto extends BaseDto<Long> {

    private UserDto user;

    private BatchDto batch;

    private Set<StudentReportDto> studentReports;

    private Set<StudentTransactionDto> studentTransactions;

}
