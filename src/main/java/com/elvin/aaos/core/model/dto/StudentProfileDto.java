package com.elvin.aaos.core.model.dto;

import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import com.elvin.aaos.core.model.enums.Gender;

@Getter
@Setter
public class StudentProfileDto extends BaseDto<Long> {

    private String fullName;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private String telephoneNumber;

    private String mobileNumber;

    private String parentMobileNumber;

    private String parentEmail;

    private String motherName;

    private String motherContactNumber;

    private String fatherName;

    private String fatherContactNumber;

    private UserDto user;

    private BatchDto batch;

}
