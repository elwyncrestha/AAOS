package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CourseDto extends BaseDto<Long> {

    private String name;

    private Set<ModuleDto> modules = new HashSet<>();

    private Set<StudentTransactionDto> studentTransactions = new HashSet<>();

}
