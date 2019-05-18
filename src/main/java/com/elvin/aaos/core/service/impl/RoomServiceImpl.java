package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.repository.RoomRepository;
import com.elvin.aaos.core.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public long countRoomsByRoomType(RoomType roomType) {
        return roomRepository.countRoomsByRoomType(roomType);
    }
}
