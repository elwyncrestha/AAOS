package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.util.Date;

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
