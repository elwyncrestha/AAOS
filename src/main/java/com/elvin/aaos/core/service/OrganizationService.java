package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.OrganizationDto;
import com.elvin.aaos.core.model.entity.User;

public interface OrganizationService {

    OrganizationDto getOrganizationDetail();

    OrganizationDto getById(long id);

    OrganizationDto save(OrganizationDto organizationDto, User user);

    OrganizationDto update(OrganizationDto organizationDto, User user);

}
