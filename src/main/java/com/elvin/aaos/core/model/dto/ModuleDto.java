package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ModuleDto extends BaseDto<Long> {

    private String name;

    private CourseDto courseDto;

    private Set<TeacherProfileDto> teacherProfileDtos = new HashSet<>();

    private Set<ExamDto> examDtos = new HashSet<>();

    private Set<StudentReportDto> studentReportDtos = new HashSet<>();

}
