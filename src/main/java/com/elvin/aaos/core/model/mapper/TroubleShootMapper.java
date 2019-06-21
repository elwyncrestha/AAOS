package com.elvin.aaos.core.model.mapper;

import org.mapstruct.Mapper;

import com.elvin.aaos.core.model.dto.TroubleShootDto;
import com.elvin.aaos.core.model.entity.TroubleShoot;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class TroubleShootMapper extends BaseMapper<TroubleShoot, TroubleShootDto> {

}
