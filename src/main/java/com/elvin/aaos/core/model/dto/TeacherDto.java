package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TeacherDto extends BaseDto<Long> {

    private String fullName;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private Gender gender;

    private String address;

    private String mobileNumber;

    private String education;

    private UserDto user;

}
