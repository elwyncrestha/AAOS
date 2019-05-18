package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.enums.RoomType;

public interface RoomService {

    long countRoomsByRoomType(RoomType roomType);

}
