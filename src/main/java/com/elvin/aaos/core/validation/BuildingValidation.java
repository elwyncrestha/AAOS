package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.model.repository.BuildingRepository;
import com.elvin.aaos.web.error.BuildingError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingValidation {

    @Autowired
    BuildingRepository buildingRepository;

    private BuildingError buildingError = new BuildingError();
    private boolean valid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BuildingError saveOrEditValidation(BuildingDto buildingDto) {
        valid = true;

        if (buildingRepository.findBuildingByName(buildingDto.getName()) != null) {
            logger.debug("BUILDING NAME ALREADY EXISTS");
            valid = false;
            buildingError.setName("building name already exists");
        }

        buildingError.setValid(valid);
        return buildingError;
    }

}
