package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class CourseRepositoryTest extends BaseTest {

    @Autowired
    CourseRepository courseRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml"})
    public void testCountCoursesByStatusShouldReturnValue() {
        long count = courseRepository.countCoursesByStatus(Status.ACTIVE);
        assertThat(count, equalTo(2L));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml"})
    public void testFindCourseByName() {
        String name = "Course 1";
        Course course = courseRepository.findCourseByName(name);
        assertThat(course.getName(), equalTo(name));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml"})
    public void testFindCourseById() {
        long id = 3L;
        Course course = courseRepository.findCourseById(id);
        assertThat(course.getId(), equalTo(id));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml"})
    public void testFindCoursesByStatus() {
        List<Course> courseList = courseRepository.findCoursesByStatus(Status.ACTIVE);
        assertThat(courseList, hasSize(2));
    }

}
