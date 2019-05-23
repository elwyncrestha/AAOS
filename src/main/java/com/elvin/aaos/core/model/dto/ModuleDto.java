package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ModuleDto extends BaseDto<Long> {

    private String name;

    private CourseDto course;

    private Set<TeacherProfileDto> teacherProfiles = new HashSet<>();

    private Set<ExamDto> exams = new HashSet<>();

    private Set<StudentReportDto> studentReports = new HashSet<>();

}
