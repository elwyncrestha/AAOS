package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, Long> {

    @Query("select tp from TeacherProfile tp where tp.user.id=?1")
    TeacherProfile findByUserId(long userId);

    TeacherProfile findTeacherProfileById(long id);

    @Query("select COUNT(tp) from TeacherProfile tp where tp.module!=null")
    long countTeacherWithModule();

    @Query("select COUNT(tp) from TeacherProfile tp where tp.module=null")
    long countTeacherWithoutModule();

}
