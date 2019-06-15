package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.RoomScheduleDetailDto;
import com.elvin.aaos.core.model.dto.RoomScheduleDto;
import com.elvin.aaos.core.model.entity.*;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.RoomScheduleDetailMapper;
import com.elvin.aaos.core.model.mapper.RoomScheduleMapper;
import com.elvin.aaos.core.model.repository.RoomScheduleRepository;
import com.elvin.aaos.core.service.RoomScheduleService;
import com.elvin.aaos.core.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
