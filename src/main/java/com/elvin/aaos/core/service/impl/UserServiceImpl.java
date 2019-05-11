package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.converter.impl.UserConverter;
import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.model.repository.UserRepository;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserConverter userConverter;

    public UserDto save(UserDto userDto, User createdBy) {

        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserType(userDto.getUserType());
        user.setStatus(Status.ACTIVE);
        user.setTimeZone(StringConstants.STATIC_TIMEZONE);

        UserType userType = userDto.getUserType();
        if (userType.equals(UserType.ADMIN)) {
            user.setAuthority(Authorities.ROLE_AUTHENTICATED + "," + Authorities.ROLE_ADMINISTRATOR);
        }

        return userConverter.convertToDto(userRepository.save(user));

    }

    public List<UserDto> list() {
        return userConverter.convertToDtoList(userRepository.findByStatusExcept(Status.DELETED));
    }

}
