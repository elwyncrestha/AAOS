package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    long countCoursesByStatus(Status status);

    Course findCourseByName(String name);

    Course findCourseById(long id);

    List<Course> findCoursesByStatus(Status status);

}
