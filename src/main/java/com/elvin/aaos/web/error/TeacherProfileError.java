package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherProfileError {

    private boolean isValid;

    private String fullName;

    private String email;

    private String dob;

    private String gender;

    private String address;

    private String mobileNumber;

    private String education;

}
