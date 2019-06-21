package com.elvin.aaos.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elvin.aaos.core.model.dto.TroubleShootDto;
import com.elvin.aaos.core.model.entity.TroubleShoot;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.TroubleShootMapper;
import com.elvin.aaos.core.model.repository.TroubleShootRepository;
import com.elvin.aaos.core.service.TroubleShootService;

@Service
public class TroubleShootServiceImpl implements TroubleShootService {

    private final TroubleShootMapper troubleShootMapper;
    private final TroubleShootRepository troubleShootRepository;

    public TroubleShootServiceImpl(
        @Autowired TroubleShootMapper troubleShootMapper,
        @Autowired TroubleShootRepository troubleShootRepository
    ) {
        this.troubleShootMapper = troubleShootMapper;
        this.troubleShootRepository = troubleShootRepository;
    }

    @Override
    public TroubleShootDto save(TroubleShootDto troubleShootDto, User createdBy) {
        TroubleShoot troubleShoot = troubleShootMapper.mapDtoToEntity(troubleShootDto);
        troubleShoot.setStatus(Status.ACTIVE);
        troubleShoot.setUser(createdBy);
        troubleShoot.setCreatedBy(createdBy);
        return troubleShootMapper.mapEntityToDto(troubleShootRepository.save(troubleShoot));
    }

    @Override
    public List<TroubleShootDto> list() {
        return troubleShootMapper.mapEntitiesToDtos(troubleShootRepository.findAllByStatus(Status.ACTIVE));
    }

    @Override
    public TroubleShootDto getById(long id) {
        return troubleShootMapper.mapEntityToDto(troubleShootRepository.findTroubleShootById(id));
    }

    @Override
    public void delete(long id, User deletedBy) {
        TroubleShoot troubleShoot = troubleShootRepository.findTroubleShootById(id);
        troubleShoot.setStatus(Status.DELETED);
        troubleShoot.setLastModifiedAt(new Date());
        troubleShoot.setModifiedBy(deletedBy);
        troubleShootRepository.save(troubleShoot);
    }
}
