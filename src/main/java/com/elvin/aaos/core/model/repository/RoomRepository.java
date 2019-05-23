package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Room;
import com.elvin.aaos.core.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    long countRoomsByRoomType(RoomType roomType);

}
