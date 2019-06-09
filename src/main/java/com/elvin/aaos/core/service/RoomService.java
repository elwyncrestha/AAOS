package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.RoomBuildingDto;
import com.elvin.aaos.core.model.dto.RoomDto;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.RoomType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomService {

    long countRoomsByRoomType(RoomType roomType);

    RoomDto save(RoomDto roomDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<RoomBuildingDto> list();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    RoomBuildingDto getById(long roomId);

    void delete(long roomId, User deletedBy);

    RoomDto update(RoomDto roomDto, User modifiedBy);

    boolean hasAssociatedBuilding(long buildingId);
}
