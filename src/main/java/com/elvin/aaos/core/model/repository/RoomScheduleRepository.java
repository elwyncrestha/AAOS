package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.RoomSchedule;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long> {

    @Query("select rs from RoomSchedule rs where rs.status=?1 order by rs.batch.id")
    List<RoomSchedule> findRoomSchedulesByStatus(Status status);

}
