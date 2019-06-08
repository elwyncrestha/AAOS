package com.elvin.aaos.web.utility.auth;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthenticationUtil {

    public static final com.elvin.aaos.core.model.entity.User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            return null;

        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            com.elvin.aaos.core.model.entity.User amtUser = new com.elvin.aaos.core.model.entity.User();
            Collection<GrantedAuthority> authorities = user.getAuthorities();
            amtUser.setUsername(user.getUsername());
            amtUser.setPassword(user.getPassword());

            int count = 0;
            for (GrantedAuthority authority : authorities) {
                if (count == 0) {
                    amtUser.setAuthority(authority.getAuthority());
                    count++;
                } else {
                    amtUser.setAuthority(amtUser.getAuthority() + "," + authority.getAuthority());
                }
            }

            return amtUser;
        }

        return null;
    }

    public static boolean currentUserIsNull() {
        return getCurrentUser() == null;
    }

    public static boolean isAdmin() {
        if (currentUserIsNull()) {
            return false;
        }

        boolean isAdmin = false;
        String authorities = getCurrentUser().getAuthority();
        if (StringUtils.isEmpty(authorities)) {
            isAdmin = false;
        } else if (authorities.contains(Authorities.ROLE_AUTHENTICATED) && authorities.contains(Authorities.ROLE_ADMINISTRATOR)) {
            isAdmin = true;
        }

        return isAdmin;
    }

    public static boolean checkCurrentUserAuthority(UserType userType) {
        if (currentUserIsNull()) {
            return false;
        }

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

        String currentAuthority = getCurrentUser().getAuthority();

        if (StringUtils.isEmpty(currentAuthority)) {
            return false;
        }

        return currentAuthority.equals(authorities);
    }


}
