package com.elvin.aaos.core.model.dto;

import java.time.DayOfWeek;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.elvin.aaos.core.model.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomScheduleDto extends BaseDto<Long> {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private String strStartTime;

    private String strEndTime;

    private long roomId;

    private long batchId;

    private long teacherProfileId;

    private String name;

    private Status status;

}
