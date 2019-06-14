package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.ModuleMapper;
import com.elvin.aaos.core.model.repository.ModuleRepository;
import com.elvin.aaos.core.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    public ModuleServiceImpl(
            @Autowired ModuleRepository moduleRepository,
            @Autowired ModuleMapper moduleMapper
    ) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
    }

    @Override
    public boolean hasAssociatedCourse(long courseId) {
        return moduleRepository.findAllByCourseId(courseId).size() > 0;
    }

    @Override
    public long countModuleByStatus(Status status) {
        return moduleRepository.countModulesByStatus(status);
    }

    @Override
    public ModuleDto save(ModuleDto moduleDto, User createdBy) {
        Module module = moduleMapper.mapDtoToEntity(moduleDto);
        module.setStatus(Status.ACTIVE);
        module.setCreatedBy(createdBy);
        return moduleMapper.mapEntityToDto(moduleRepository.save(module));
    }

    @Override
    public List<ModuleDto> list() {
        return moduleMapper.mapEntitiesToDtos(moduleRepository.findModulesByStatus(Status.ACTIVE));
    }
}
