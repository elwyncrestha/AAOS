package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BatchDto extends BaseDto<Long> {

    private String name;

    private Set<StudentProfileDto> studentProfiles = new HashSet<>();

    private Set<CourseDto> courses;

    private Set<ExamDto> exams;

    private Set<RoomScheduleDto> roomSchedules = new HashSet<>();

}
