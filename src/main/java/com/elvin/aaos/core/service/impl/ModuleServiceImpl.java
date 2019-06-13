package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.repository.ModuleRepository;
import com.elvin.aaos.core.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleServiceImpl(
            @Autowired ModuleRepository moduleRepository
    ) {
        this.moduleRepository = moduleRepository;
    }

    @Override
    public boolean hasAssociatedCourse(long courseId) {
        return moduleRepository.findAllByCourseId(courseId).size() > 0;
    }
}
