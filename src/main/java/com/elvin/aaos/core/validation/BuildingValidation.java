package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.entity.Building;
import com.elvin.aaos.core.model.repository.BuildingRepository;
import com.elvin.aaos.web.error.BuildingError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingValidation {

    private final BuildingRepository buildingRepository;
    private BuildingError buildingError = new BuildingError();
    private boolean valid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BuildingValidation(
            @Autowired BuildingRepository buildingRepository
    ) {
        this.buildingRepository = buildingRepository;
    }

    public BuildingError saveValidation(BuildingDto buildingDto) {
        valid = true;

        buildingError.setName(checkName(buildingDto.getName()));
        buildingError.setValid(valid);
        return buildingError;
    }

    public BuildingError updateValidation(BuildingDto buildingDto) {

        Building building = buildingRepository.findBuildingById(buildingDto.getId());
        valid = true;

        if (StringUtils.isBlank(buildingDto.getName()) || !building.getName().equals(buildingDto.getName())) {
            buildingError.setName(checkName(buildingDto.getName()));
        }

        buildingError.setValid(valid);
        return buildingError;
    }

    private String checkName(String name) {
        if (StringUtils.isNotBlank(name)){
            if (buildingRepository.findBuildingByName(name) != null) {
                logger.debug("BUILDING NAME ALREADY EXISTS");
                valid = false;
                buildingError.setName("building name already exists");
            }
        } else {
            logger.debug("BUILDING NAME CANNOT BE NULL OR EMPTY");
            valid = false;
            return "building name cannot be null or empty";
        }

        return "";
    }

}
