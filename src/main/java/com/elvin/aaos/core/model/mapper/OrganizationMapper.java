package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.OrganizationDto;
import com.elvin.aaos.core.model.entity.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class OrganizationMapper extends BaseMapper<Organization, OrganizationDto> {

}
