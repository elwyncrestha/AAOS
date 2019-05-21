package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.util.Date;

@Getter
@Setter
public class RoomScheduleDto extends BaseDto<Long> {

    private DayOfWeek dayOfWeek;

    @DateTimeFormat(pattern = "hh:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "hh:mm:ss")
    private Date endTime;

    private String name;

    private RoomDto room;

    private BatchDto batch;

    private TeacherProfileDto teacherProfile;

}
