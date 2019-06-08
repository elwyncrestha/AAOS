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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Start(
            @Autowired UserRepository userRepository,
            @Autowired PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kathmandu");

        createUser(timeZone, StringConstants.USER_ADMIN, StringConstants.USER_ADMIN, StringConstants.ADMIN_EMAIL, "12345678", UserType.SUPERADMIN, Authorities.ROLE_AUTHENTICATED + "," + Authorities.ROLE_ADMINISTRATOR, Status.ACTIVE);
    }

    private void createUser(TimeZone timeZone, String username, String fullName, String email, String password, UserType userType, String authorities, Status status) {
        try {
            User user = userRepository.findByUsername(username);

            if (user == null) {

                User dto = new User();
                dto.setTimeZone(timeZone.getID());
                dto.setUsername(username);
                dto.setFullName(fullName);
                dto.setEmail(email);
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
