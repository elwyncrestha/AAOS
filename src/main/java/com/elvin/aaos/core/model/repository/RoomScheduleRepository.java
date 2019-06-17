package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.RoomSchedule;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long> {

    @Query("select rs from RoomSchedule rs where rs.status=?1 order by rs.batch.id")
    List<RoomSchedule> findRoomSchedulesByStatus(Status status);

    RoomSchedule findRoomScheduleById(long id);

    @Query("select COUNT(rs) from RoomSchedule rs where rs.batch.id=?1")
    long countAllByBatchId(long batchId);

    @Query("select COUNT(rs) from RoomSchedule rs where rs.room.id=?1")
    long countAllByRoomId(long roomId);

    @Query("select rs from RoomSchedule rs where rs.status=?1 and rs.room.roomType=?2 order by rs.room.name")
    List<RoomSchedule> findRoomSchedulesByStatusAndRoomType(Status status, RoomType roomType);

}
