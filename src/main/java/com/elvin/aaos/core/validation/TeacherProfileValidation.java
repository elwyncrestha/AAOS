package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.TeacherProfileDto;
import com.elvin.aaos.web.error.TeacherProfileError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherProfileValidation {

    private final DateValidation dateValidation;
    private TeacherProfileError teacherProfileError = new TeacherProfileError();
    private boolean valid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TeacherProfileValidation(
            @Autowired DateValidation dateValidation
    ) {
        this.dateValidation = dateValidation;
    }

    public TeacherProfileError saveOrEditValidation(TeacherProfileDto teacherProfileDto) {
        valid = true;

        if (!dateValidation.isPastDate(teacherProfileDto.getDob())) {
            String dobError = "Date of Birth must not be future date.";
            teacherProfileError.setDob(dobError);
            logger.debug(dobError);
            valid = false;
        }

        teacherProfileError.setValid(valid);
        return teacherProfileError;
    }

}
