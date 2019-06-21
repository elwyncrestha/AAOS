package com.elvin.aaos.core.service.impl;

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
}
