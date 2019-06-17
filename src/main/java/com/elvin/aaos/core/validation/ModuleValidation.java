package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.repository.ModuleRepository;
import com.elvin.aaos.web.error.ModuleError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleValidation {

    private final ModuleRepository moduleRepository;
    private ModuleError moduleError = new ModuleError();
    private boolean valid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ModuleValidation(@Autowired ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public ModuleError saveValidation(ModuleDto moduleDto) {
        valid = true;

        moduleError.setName(checkName(moduleDto.getName()));
        moduleError.setValid(valid);
        return moduleError;
    }

    public ModuleError updateValidation(ModuleDto moduleDto) {

        Module module = moduleRepository.findModuleById(moduleDto.getId());
        valid = true;

        if (StringUtils.isBlank(moduleDto.getName()) || !module.getName().equals(moduleDto.getName())) {
            moduleError.setName(checkName(moduleDto.getName()));
        }

        moduleError.setValid(valid);
        return moduleError;

    }

    private String checkName(String name) {
        if (StringUtils.isNotBlank(name)){
            if (moduleRepository.findModuleByName(name) != null) {
                logger.debug("MODULE NAME ALREADY EXISTS");
                valid = false;
                return "module name already exists";
            }
        } else {
            logger.debug("MODULE NAME CANNOT BE NULL OR EMPTY");
            valid = false;
            return "module name cannot be null or empty";
        }

        return "";
    }

}
