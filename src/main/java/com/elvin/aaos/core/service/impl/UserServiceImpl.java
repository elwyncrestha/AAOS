package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.model.mapper.UserMapper;
import com.elvin.aaos.core.model.repository.UserRepository;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(@Autowired UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDto save(UserDto userDto, User createdBy) {

        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserType(userDto.getUserType());
        user.setStatus(Status.ACTIVE);
        user.setTimeZone(StringConstants.STATIC_TIMEZONE);
        user.setAuthority(getUserAuthorityByUserType(userDto.getUserType()));
        user.setCreatedBy(createdBy);

        return userMapper.mapEntityToDto(userRepository.save(user));

    }

    public List<UserDto> list() {
        return userMapper.mapEntitiesToDtos(userRepository.findByStatusExcept(Status.DELETED));
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findUserById(id);
        user.setStatus(Status.DELETED);
        user.setLastModifiedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(long id) {
        return userMapper.mapEntityToDto(userRepository.findUserById(id));
    }

    @Override
    public UserDto update(UserDto userDto, User modifiedBy) {
        User user = userRepository.findUserById(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserType(userDto.getUserType());
        user.setStatus(userDto.getStatus());
        user.setAuthority(getUserAuthorityByUserType(userDto.getUserType()));
        user.setModifiedBy(modifiedBy);

        return userMapper.mapEntityToDto(userRepository.save(user));
    }

    @Override
    public String getUserAuthorityByUserType(UserType userType) {
        String authorities = Authorities.ROLE_AUTHENTICATED;

        if (userType.equals(UserType.ADMIN)) {
            authorities = authorities + "," + Authorities.ROLE_ADMINISTRATOR;
        } else if (userType.equals(UserType.STUDENT)) {
            authorities = authorities + "," + Authorities.ROLE_STUDENT;
        } else if (userType.equals(UserType.TEACHER)) {
            authorities = authorities + "," + Authorities.ROLE_TEACHER;
        } else if (userType.equals(UserType.ACADEMIC_STAFF)) {
            authorities = authorities + "," + Authorities.ROLE_ACADEMIC_STAFF;
        } else if (userType.equals(UserType.OPERATIONAL_STAFF)) {
            authorities = authorities + "," + Authorities.ROLE_OPERATIONAL_STAFF;
        }

        return authorities;
    }

}
