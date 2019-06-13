package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.CourseDto;
import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.CourseMapper;
import com.elvin.aaos.core.model.repository.CourseRepository;
import com.elvin.aaos.core.service.CourseService;
import com.elvin.aaos.web.utility.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(
            @Autowired CourseRepository courseRepository,
            @Autowired CourseMapper courseMapper
    ) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public long countTotalCourse() {
        return courseRepository.countCoursesByStatus(Status.ACTIVE);
    }

    @Override
    public CourseDto save(CourseDto courseDto, User createdBy) {
        Course course = courseMapper.mapDtoToEntity(courseDto);
        course.setStatus(Status.ACTIVE);
        course.setCreatedBy(createdBy);

        return courseMapper.mapEntityToDto(courseRepository.save(course));
    }

    @Override
    public List<CourseDto> list() {
        return courseMapper.mapEntitiesToDtos(courseRepository.findCoursesByStatus(Status.ACTIVE));
    }

    @Override
    public CourseDto getById(long id) {
        return courseMapper.mapEntityToDto(courseRepository.findCourseById(id));
    }

    @Override
    public void delete(long id, User deletedBy) {
        Course course = courseRepository.findCourseById(id);
        course.setStatus(Status.DELETED);
        course.setName(StringConstants.DELETED_COURSE + course.getId() + "_" + course.getName());
        course.setLastModifiedAt(new Date());
        course.setModifiedBy(deletedBy);
        courseRepository.save(course);
    }

    @Override
    public CourseDto update(CourseDto courseDto, User modifiedBy) {
        Course course = courseRepository.findCourseById(courseDto.getId());
        course.setName(courseDto.getName());
        course.setModifiedBy(modifiedBy);
        course.setLastModifiedAt(new Date());

        return courseMapper.mapEntityToDto(courseRepository.save(course));
    }
}
