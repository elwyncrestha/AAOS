package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamError {

    private boolean valid;

    private String name;

    private String start;

    private String end;

    private String startTime;

    private String endTime;

    private String module;

}
