package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BatchDto extends BaseDto<Long> {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date formationDate;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private Set<StudentProfileDto> studentProfiles = new HashSet<>();

    private Set<CourseDto> courses;

    private Set<ExamDto> exams;

    private Set<RoomScheduleDto> roomSchedules = new HashSet<>();

}
