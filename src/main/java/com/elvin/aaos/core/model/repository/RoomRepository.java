package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Room;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    long countRoomsByRoomType(RoomType roomType);

    Room findRoomByName(String name);

    @Query("SELECT r FROM Room r where r.status!=?1")
    List<Room> findByStatusExcept(Status status);

}
