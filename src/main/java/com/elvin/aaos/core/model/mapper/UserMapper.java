package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.BaseMapper;
import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class UserMapper extends BaseMapper<User, UserDto> {

}
