package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.RoomDto;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.RoomType;

public interface RoomService {

    long countRoomsByRoomType(RoomType roomType);

    RoomDto save(RoomDto roomDto, User createdBy);
}
