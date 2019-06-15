package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.RoomScheduleDetailDto;
import com.elvin.aaos.core.model.dto.RoomScheduleDto;
import com.elvin.aaos.core.model.entity.*;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.RoomScheduleDetailMapper;
import com.elvin.aaos.core.model.mapper.RoomScheduleMapper;
import com.elvin.aaos.core.model.repository.RoomScheduleRepository;
import com.elvin.aaos.core.service.RoomScheduleService;
import com.elvin.aaos.core.utility.DateUtils;
import com.elvin.aaos.web.utility.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomScheduleServiceImpl implements RoomScheduleService {

    private final RoomScheduleMapper roomScheduleMapper;
    private final RoomScheduleRepository roomScheduleRepository;
    private final RoomScheduleDetailMapper roomScheduleDetailMapper;

    public RoomScheduleServiceImpl(
            @Autowired RoomScheduleMapper roomScheduleMapper,
            @Autowired RoomScheduleRepository roomScheduleRepository,
            @Autowired RoomScheduleDetailMapper roomScheduleDetailMapper
    ) {
        this.roomScheduleMapper = roomScheduleMapper;
        this.roomScheduleRepository = roomScheduleRepository;
        this.roomScheduleDetailMapper = roomScheduleDetailMapper;
    }

    @Override
    public RoomScheduleDto save(RoomScheduleDto roomScheduleDto, User createdBy) {
        RoomSchedule roomSchedule = roomScheduleMapper.mapDtoToEntity(roomScheduleDto);
        roomSchedule.setStartTime(DateUtils.convertDateTime(roomScheduleDto.getStrStartTime(), DateUtils.HH_mm));
        roomSchedule.setEndTime(DateUtils.convertDateTime(roomScheduleDto.getStrEndTime(), DateUtils.HH_mm));
        Room room = new Room();
        room.setId(roomScheduleDto.getRoomId());
        roomSchedule.setRoom(room);
        Batch batch = new Batch();
        batch.setId(roomScheduleDto.getBatchId());
        roomSchedule.setBatch(batch);
        TeacherProfile teacherProfile = new TeacherProfile();
        teacherProfile.setId(roomScheduleDto.getTeacherProfileId());
        roomSchedule.setTeacherProfile(teacherProfile);
        roomSchedule.setStatus(Status.ACTIVE);
        roomSchedule.setCreatedBy(createdBy);
        return roomScheduleMapper.mapEntityToDto(roomScheduleRepository.save(roomSchedule));
    }

    @Override
    public List<RoomScheduleDetailDto> list() {
        return roomScheduleDetailMapper.mapEntitiesToDtos(roomScheduleRepository.findRoomSchedulesByStatus(Status.ACTIVE));
    }

    @Override
    public RoomScheduleDetailDto getById(long id) {
        return roomScheduleDetailMapper.mapEntityToDto(roomScheduleRepository.findRoomScheduleById(id));
    }

    @Override
    public void delete(long id, User deletedBy) {
        RoomSchedule roomSchedule = roomScheduleRepository.findRoomScheduleById(id);
        roomSchedule.setStatus(Status.DELETED);
        roomSchedule.setName(StringConstants.DELETED_ROOM_SCHEDULE + roomSchedule.getId() + "_" + roomSchedule.getName());
        roomSchedule.setBatch(null);
        roomSchedule.setRoom(null);
        roomSchedule.setTeacherProfile(null);
        roomSchedule.setModifiedBy(deletedBy);
        roomSchedule.setLastModifiedAt(new Date());
        roomScheduleRepository.save(roomSchedule);
    }

    @Override
    public boolean hasAssociatedBatch(long batchId) {
        return roomScheduleRepository.countAllByBatchId(batchId) > 0;
    }

    @Override
    public boolean hasAssociatedRoom(long roomId) {
        return roomScheduleRepository.countAllByRoomId(roomId) > 0;
    }

    @Override
    public RoomScheduleDto update(RoomScheduleDto roomScheduleDto, User modifiedBy) {
        RoomSchedule roomSchedule = roomScheduleRepository.findRoomScheduleById(roomScheduleDto.getId());
        roomSchedule.setStartTime(DateUtils.convertDateTime(roomScheduleDto.getStrStartTime(), DateUtils.HH_mm));
        roomSchedule.setEndTime(DateUtils.convertDateTime(roomScheduleDto.getStrEndTime(), DateUtils.HH_mm));
        Room room = new Room();
        room.setId(roomScheduleDto.getRoomId());
        roomSchedule.setRoom(room);
        Batch batch = new Batch();
        batch.setId(roomScheduleDto.getBatchId());
        roomSchedule.setBatch(batch);
        TeacherProfile teacherProfile = new TeacherProfile();
        teacherProfile.setId(roomScheduleDto.getTeacherProfileId());
        roomSchedule.setTeacherProfile(teacherProfile);
        roomSchedule.setModifiedBy(modifiedBy);
        roomSchedule.setLastModifiedAt(new Date());
        return roomScheduleMapper.mapEntityToDto(roomScheduleRepository.save(roomSchedule));
    }

    @Override
    public List<RoomScheduleDetailDto> list(RoomType roomType) {
        return roomScheduleDetailMapper.mapEntitiesToDtos(roomScheduleRepository.findRoomSchedulesByStatusAndRoomType(Status.ACTIVE, roomType));
    }
}
