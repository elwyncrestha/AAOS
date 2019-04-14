package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.entity.User;

public interface UserService {

    UserDto save(UserDto userDto, User createdBy);

}
