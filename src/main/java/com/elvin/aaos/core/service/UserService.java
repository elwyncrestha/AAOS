package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.entity.User;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto, User createdBy);

    List<UserDto> list();

    void delete(long id);

    UserDto getUser(long id);

    UserDto update(UserDto userDto, User modifiedBy);

}
