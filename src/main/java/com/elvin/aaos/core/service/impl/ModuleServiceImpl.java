package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.ModuleCourseDto;
import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.ModuleCourseMapper;
import com.elvin.aaos.core.model.mapper.ModuleMapper;
import com.elvin.aaos.core.model.repository.ModuleRepository;
import com.elvin.aaos.core.service.ModuleService;
import com.elvin.aaos.web.utility.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;
    private final ModuleCourseMapper moduleCourseMapper;

    public ModuleServiceImpl(
            @Autowired ModuleRepository moduleRepository,
            @Autowired ModuleMapper moduleMapper,
            @Autowired ModuleCourseMapper moduleCourseMapper
    ) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
        this.moduleCourseMapper = moduleCourseMapper;
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
        Course course = new Course();
        course.setId(moduleDto.getCourseId());
        module.setCourse(course);
        module.setCreatedBy(createdBy);
        return moduleMapper.mapEntityToDto(moduleRepository.save(module));
    }

    @Override
    public List<ModuleCourseDto> list() {
        return moduleCourseMapper.mapEntitiesToDtos(moduleRepository.findModulesByStatus(Status.ACTIVE));
    }

    @Override
    public ModuleCourseDto getById(long id) {
        return moduleCourseMapper.mapEntityToDto(moduleRepository.findModuleById(id));
    }

    @Override
    public ModuleDto getModuleById(long id) {
        return moduleMapper.mapEntityToDto(moduleRepository.findModuleById(id));
    }

    @Override
    public void delete(long id, User deletedBy) {
        Module module = moduleRepository.findModuleById(id);
        module.setStatus(Status.DELETED);
        module.setName(StringConstants.DELETED_MODULE + module.getId() + "_" + module.getName());
        module.setCourse(null);
        module.setLastModifiedAt(new Date());
        module.setModifiedBy(deletedBy);
        moduleRepository.save(module);
    }

    @Override
    public ModuleDto update(ModuleDto moduleDto, User modifiedBy) {
        Module module = moduleRepository.findModuleById(moduleDto.getId());
        module.setName(moduleDto.getName());
        Course course = new Course();
        course.setId(moduleDto.getCourseId());
        module.setCourse(course);
        module.setModifiedBy(modifiedBy);
        module.setLastModifiedAt(new Date());
        return moduleMapper.mapEntityToDto(moduleRepository.save(module));
    }
}
