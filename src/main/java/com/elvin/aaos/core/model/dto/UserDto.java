package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto<Long>{

    private String fullName;

    private String username;

    private String password;

    private String email;

    private UserType userType;

    private String authority;

    private Status status;

    private String timeZone;

}
