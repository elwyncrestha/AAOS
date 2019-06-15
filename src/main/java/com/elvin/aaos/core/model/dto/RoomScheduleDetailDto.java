package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.util.Date;

@Getter
@Setter
public class RoomScheduleDetailDto extends BaseDto<Long> {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Date startTime;

    private Date endTime;

    private RoomDto room;

    private BatchDto batch;

    private TeacherDto teacherProfile;

    private String name;

    private Status status;

}
