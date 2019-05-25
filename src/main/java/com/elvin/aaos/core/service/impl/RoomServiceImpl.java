package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.RoomBuildingDto;
import com.elvin.aaos.core.model.dto.RoomDto;
import com.elvin.aaos.core.model.entity.Room;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.RoomBuildingMapper;
import com.elvin.aaos.core.model.mapper.RoomMapper;
import com.elvin.aaos.core.model.repository.BuildingRepository;
import com.elvin.aaos.core.model.repository.RoomRepository;
import com.elvin.aaos.core.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomBuildingMapper roomBuildingMapper;

    @Override
    public long countRoomsByRoomType(RoomType roomType) {
        return roomRepository.countRoomsByRoomType(roomType);
    }

    @Override
    public RoomDto save(RoomDto roomDto, User createdBy) {
        Room room = roomMapper.mapDtoToEntity(roomDto);
        room.setStatus(Status.ACTIVE);
        room.setCreatedBy(createdBy);
        room.setBuilding(buildingRepository.findBuildingById(room.getBuildingId()));

        return roomMapper.mapEntityToDto(roomRepository.save(room));
    }

    @Override
    public List<RoomBuildingDto> list() {
        return roomBuildingMapper.mapEntitiesToDtos(roomRepository.findByStatusExcept(Status.DELETED));
    }

    @Override
    public RoomBuildingDto getById(long roomId) {
        return roomBuildingMapper.mapEntityToDto(roomRepository.findRoomById(roomId));
    }

    @Override
    public void delete(long roomId, User deletedBy) {
        Room room = roomRepository.findRoomById(roomId);
        room.setStatus(Status.DELETED);
        room.setLastModifiedAt(new Date());
        room.setModifiedBy(deletedBy);
        roomRepository.save(room);
    }
}