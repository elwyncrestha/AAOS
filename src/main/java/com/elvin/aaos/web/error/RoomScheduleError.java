package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomScheduleError {

    private boolean valid;

    private String dayOfWeek;

    private String startTime;

    private String endTime;

    private String room;

    private String batch;

    private String teacher;

    private String name;

}
