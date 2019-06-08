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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired PasswordEncoder passwordEncoder,
            @Autowired UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDto save(UserDto userDto, User createdBy) {

        User user = userMapper.mapDtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
    public void delete(long id, User deletedBy) {
        User user = userRepository.findUserById(id);
        user.setStatus(Status.DELETED);
        user.setUsername(StringConstants.DELETED_USER + user.getId() + "_" + user.getUsername());
        user.setEmail(StringConstants.DELETED_USER + user.getId() + "_" + user.getEmail());
        user.setLastModifiedAt(new Date());
        user.setModifiedBy(deletedBy);
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
        user.setLastModifiedAt(new Date());

        return userMapper.mapEntityToDto(userRepository.save(user));
    }

    @Override
    public String getUserAuthorityByUserType(UserType userType) {
        String authorities = Authorities.ROLE_AUTHENTICATED;

        if (userType.equals(UserType.SUPERADMIN) || userType.equals(UserType.ADMIN)) {
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

    @Override
    public long countByUserType(UserType userType) {
        return userRepository.countUsersByUserType(userType);
    }

    @Override
    public long countAllStaffs() {
        long totalStaffs = 0;
        totalStaffs += userRepository.countUsersByUserType(UserType.ACADEMIC_STAFF);
        totalStaffs += userRepository.countUsersByUserType(UserType.OPERATIONAL_STAFF);
        return totalStaffs;
    }

}
