package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileError {

    private boolean isValid;

    private String fullName;

    private String email;

    private String dob;

    private String gender;

    private String address;

    private String telephoneNumber;

    private String mobileNumber;

    private String parentMobileNumber;

    private String parentEmail;

    private String motherName;

    private String motherContactNumber;

    private String fatherName;

    private String fatherContactNumber;

}
