package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserError {

    private boolean isValid;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private String userType;

    private String authority;
}
