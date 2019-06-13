package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.CourseDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseService {

    long countTotalCourse();

    CourseDto save(CourseDto courseDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<CourseDto> list();

    CourseDto getById(long id);

    void delete(long id, User deletedBy);

    CourseDto update(CourseDto courseDto, User modifiedBy);

}
