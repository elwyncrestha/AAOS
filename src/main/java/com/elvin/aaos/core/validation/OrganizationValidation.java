package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.OrganizationDto;
import com.elvin.aaos.web.error.OrganizationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationValidation {

    private final DateValidation dateValidation;
    private boolean isValid = false;
    private OrganizationError organizationError = new OrganizationError();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public OrganizationValidation(
            @Autowired DateValidation dateValidation
    ) {
        this.dateValidation = dateValidation;
    }

    public OrganizationError saveOrEditValidation(OrganizationDto organizationDto) {

        isValid = true;

        if (!dateValidation.isPastDate(organizationDto.getEstablishment())) {
            String establishmentError = "Organization Establishment date must not be future date.";
            organizationError.setEstablishment(establishmentError);
            logger.debug(establishmentError);
            isValid = false;
        }

        organizationError.setValid(isValid);
        return organizationError;
    }

}
