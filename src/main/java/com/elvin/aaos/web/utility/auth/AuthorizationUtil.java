package com.elvin.aaos.web.utility.auth;

import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.repository.UserRepository;
import com.elvin.aaos.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationUtil {

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthorizationUtil(
            @Autowired UserService userService,
            @Autowired UserRepository userRepository
    ) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public User getUser() {
        if (AuthenticationUtil.getCurrentUser() == null) {
            return new User();
        }

        try {
            return userRepository.findByUsername(AuthenticationUtil.getCurrentUser().getUsername());
        } catch (Exception e) {
            return new User();
        }
    }

}
