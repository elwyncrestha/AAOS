package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.web.error.StudentProfileError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileValidation {

    private final DateValidation dateValidation;
    private StudentProfileError studentProfileError = new StudentProfileError();
    private boolean valid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public StudentProfileValidation(
            @Autowired DateValidation dateValidation
    ) {
        this.dateValidation = dateValidation;
    }

    public StudentProfileError saveOrEditValidation(StudentProfileDto studentProfileDto) {
        valid = true;

        if (!dateValidation.isPastDate(studentProfileDto.getDob())) {
            String dobError = "Date of Birth must not be future date.";
            studentProfileError.setDob(dobError);
            logger.debug(dobError);
            valid = false;
        }

        studentProfileError.setValid(valid);
        return studentProfileError;
    }

}
