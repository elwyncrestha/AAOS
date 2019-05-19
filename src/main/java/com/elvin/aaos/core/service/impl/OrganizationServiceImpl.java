package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.OrganizationDto;
import com.elvin.aaos.core.model.entity.Organization;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.mapper.OrganizationMapper;
import com.elvin.aaos.core.model.repository.OrganizationRepository;
import com.elvin.aaos.core.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationMapper organizationMapper;

    @Autowired
    OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(@Autowired OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    @Override
    public OrganizationDto getOrganizationDetail() {
        return organizationMapper.mapEntityToDto(organizationRepository.findFirstByOrderByIdAsc());
    }

    @Override
    public OrganizationDto getById(long id) {
        return organizationMapper.mapEntityToDto(organizationRepository.findOrganizationById(id));
    }

    @Override
    public OrganizationDto save(OrganizationDto organizationDto, User user) {

        Organization organization = organizationMapper.mapDtoToEntity(organizationDto);
        organization.setCreatedBy(user);

        return organizationMapper.mapEntityToDto(organizationRepository.save(organization));

    }

    @Override
    public OrganizationDto update(OrganizationDto organizationDto, User user) {
        Organization organization = organizationRepository.findOrganizationById(organizationDto.getId());
        organization.setName(organizationDto.getName());
        organization.setEstablishment(organizationDto.getEstablishment());
        organization.setDescription(organizationDto.getDescription());
        organization.setModifiedBy(user);

        return organizationMapper.mapEntityToDto(organizationRepository.save(organization));
    }
}
