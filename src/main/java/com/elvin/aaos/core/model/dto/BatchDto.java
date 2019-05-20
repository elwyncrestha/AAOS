package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BatchDto extends BaseDto<Long> {

    private String name;

    private Set<StudentProfileDto> studentProfileDtos = new HashSet<>();

    private Set<CourseDto> courseDtos;

    private Set<ExamDto> examDtos;

    private Set<RoomScheduleDto> roomScheduleDtos = new HashSet<>();

}
