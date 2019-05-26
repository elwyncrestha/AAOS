package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.dto.BuildingRoomDto;
import com.elvin.aaos.core.model.entity.Building;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.BuildingStatus;
import com.elvin.aaos.core.model.mapper.BuildingMapper;
import com.elvin.aaos.core.model.mapper.BuildingRoomMapper;
import com.elvin.aaos.core.model.repository.BuildingRepository;
import com.elvin.aaos.core.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    private final BuildingMapper buildingMapper;

    public BuildingServiceImpl(@Autowired BuildingMapper buildingMapper) {
        this.buildingMapper = buildingMapper;
    }

    @Override
    public long countAll() {
        return buildingRepository.countAll();
    }

    @Override
    public BuildingDto save(BuildingDto buildingDto, User user) {
        Building building = buildingMapper.mapDtoToEntity(buildingDto);
        building.setCreatedBy(user);

        return buildingMapper.mapEntityToDto(buildingRepository.save(building));
    }

    @Override
    public List<BuildingDto> list() {
        return buildingMapper.mapEntitiesToDtos(buildingRepository.findByStatusExcept(BuildingStatus.DEMOLISHED));
    }

    @Override
    public BuildingDto getById(long id) {
        return buildingMapper.mapEntityToDto(buildingRepository.findBuildingById(id));
    }

    @Override
    public void delete(long id, User deletedBy) {
        Building building = buildingRepository.findBuildingById(id);
        building.setStatus(BuildingStatus.DEMOLISHED);
        building.setLastModifiedAt(new Date());
        building.setModifiedBy(deletedBy);
        buildingRepository.save(building);
    }

    @Override
    public BuildingDto update(BuildingDto buildingDto, User modifiedBy) {
        Building building = buildingRepository.findBuildingById(buildingDto.getId());
        building.setName(buildingDto.getName());
        building.setDescription(buildingDto.getDescription());
        building.setStatus(buildingDto.getStatus());
        building.setModifiedBy(modifiedBy);
        building.setLastModifiedAt(new Date());

        return buildingMapper.mapEntityToDto(buildingRepository.save(building));
    }

    @Override
    public long countByStatus(BuildingStatus buildingStatus) {
        return buildingRepository.countByStatus(buildingStatus);
    }
}
