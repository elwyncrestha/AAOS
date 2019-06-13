package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.CourseDto;
import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.repository.CourseRepository;
import com.elvin.aaos.web.error.CourseError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseValidation {

    private final CourseRepository courseRepository;
    private CourseError courseError = new CourseError();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean valid = true;

    public CourseValidation(@Autowired CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseError saveValidation(CourseDto courseDto) {
        valid = true;

        if (StringUtils.isEmpty(courseDto.getName())) {
            courseError.setName("course name cannot be empty");
            logger.debug("COURSE NAME CANNOT BE EMPTY");
            valid = false;
        }
        else if (courseRepository.findCourseByName(courseDto.getName()) != null) {
            courseError.setName("course already exists");
            logger.debug("COURSE ALREADY EXISTS");
            valid = false;
        }

        courseError.setValid(valid);
        return courseError;
    }

    public CourseError updateValidation(CourseDto courseDto) {

        Course course = courseRepository.findCourseById(courseDto.getId());
        valid = true;

        if (StringUtils.isEmpty(courseDto.getName())) {
            courseError.setName("course name cannot be empty");
            logger.debug("COURSE NAME CANNOT BE EMPTY");
            valid = false;
        }
        else if (!courseDto.getName().equals(course.getName())) {
            if (courseRepository.findCourseByName(courseDto.getName()) != null) {
                courseError.setName("course already exists");
                logger.debug("COURSE ALREADY EXISTS");
                valid = false;
            }
        }

        courseError.setValid(valid);
        return courseError;

    }

}
