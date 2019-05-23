package com.elvin.aaos.web.init;

import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.model.repository.UserRepository;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Service
public class Start {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kathmandu");

        createUser(timeZone, StringConstants.USER_ADMIN, "testAdmin", UserType.ADMIN, Authorities.ROLE_AUTHENTICATED + "," + Authorities.ROLE_ADMINISTRATOR, Status.ACTIVE);
    }

    private void createUser(TimeZone timeZone, String username, String password, UserType userType, String authorities, Status status) {
        try {
            User user = userRepository.findByUsername(username);

            if (user == null) {

                User dto = new User();
                dto.setTimeZone(timeZone.getID());
                dto.setUsername(username);
                dto.setPassword(passwordEncoder.encode(password));
                dto.setUserType(userType);
                dto.setAuthority(authorities);
                dto.setStatus(status);
                dto.setUserType(userType);

                userRepository.save(dto);
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

}
