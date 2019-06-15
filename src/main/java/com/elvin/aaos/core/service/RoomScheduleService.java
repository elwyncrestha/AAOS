package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.RoomScheduleDto;
import com.elvin.aaos.core.model.entity.User;

public interface RoomScheduleService {

    RoomScheduleDto save(RoomScheduleDto roomScheduleDto, User createdBy);

}
