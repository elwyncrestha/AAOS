package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.UserType;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto, User createdBy);

    List<UserDto> list();

    void delete(long id, User deletedBy);

    UserDto getUser(long id);

    UserDto update(UserDto userDto, User modifiedBy);

    String getUserAuthorityByUserType(UserType userType);

    long countByUserType(UserType userType);

    long countAllStaffs();

}
