package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.RoomScheduleDetailDto;
import com.elvin.aaos.core.model.dto.RoomScheduleDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomScheduleService {

    RoomScheduleDto save(RoomScheduleDto roomScheduleDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<RoomScheduleDetailDto> list();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    RoomScheduleDetailDto getById(long id);

    void delete(long id, User deletedBy);

    boolean hasAssociatedBatch(long batchId);

    boolean hasAssociatedRoom(long roomId);

    RoomScheduleDto update(RoomScheduleDto roomScheduleDto, User modifiedBy);

}
