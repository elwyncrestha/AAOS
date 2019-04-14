package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.repository.UserRepository;
import com.elvin.aaos.web.error.UserError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidation {

    boolean valid = true;

    UserError userError = new UserError();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    public UserError saveValidation(UserDto userDto) {

        valid = true;

        userError.setFullName(checkString(userDto.getFullName(), 5, 100, "full name", true));
        userError.setUsername(checkString(userDto.getUsername(), 5, 50, "username", true));
        userError.setUsername(checkUserName(userDto.getUsername()));
        userError.setEmail(checkEmailAddress(userDto.getEmail()));
        userError.setPassword(checkString(userDto.getPassword(), 8, 30, "password", true));

        userError.setValid(valid);

        return userError;
    }

    private String checkString(String value, int minLen, int maxLen, String target, boolean notNull) {
        if (notNull && value == null) {
            logger.debug(target + " NULL");
            valid = false;
            return "Cannot be null";
        }
        if (value != null) {
            if (value.length() < minLen) {
                logger.debug(target + " LESS THAN " + minLen);
                valid = false;
                return "Must be greater than " + minLen + " length";
            }
            if (value.length() > maxLen) {
                logger.debug(target + " MORE THAN " + maxLen);
                valid = false;
                return "Must be less than " + maxLen + " length";
            }
        }
        return "";
    }

    private String checkUserName(String username) {
        if (userRepository.findByUsername(username) != null) {
            logger.debug("USERNAME ALREADY EXISTS");
            valid = false;
            return "username already exists";
        }
        return "";
    }

    private String checkEmailAddress(String email) {
        if (userRepository.findByEmail(email) != null) {
            logger.debug("EMAIL ADDRESS ALREADY USED");
            valid = false;
            return "email address already used";
        }
        return "";
    }

}
