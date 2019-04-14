package com.elvin.aaos.core.model.converter.impl;

import com.elvin.aaos.core.model.converter.Converter;
import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements Converter<User, UserDto> {


    public User convertToEntity(UserDto dto) {
        return null;
    }

    public UserDto convertToDto(User entity) {

        if (entity == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(entity.getId());
        userDto.setFullName(entity.getFullName());
        userDto.setUsername(entity.getUsername());
        userDto.setEmail(entity.getEmail());
        userDto.setStatus(entity.getStatus());
        userDto.setUserType(entity.getUserType());
        userDto.setAuthority(entity.getAuthority());

        return userDto;
    }
}
