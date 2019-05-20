package com.elvin.aaos.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TeacherProfileDto extends BaseDto<Long> {

    private UserDto userDto;

    private ModuleDto moduleDto;

    private Set<RoomScheduleDto> roomSchedules = new HashSet<>();

}
