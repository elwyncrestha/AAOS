package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    @Query("select sp from StudentProfile sp where sp.user.id=?1")
    StudentProfile findByUserId(long userId);

    StudentProfile findStudentProfileById(long id);

    @Query("select COUNT(sp) from StudentProfile sp where sp.batch.id=?1")
    long countAllByBatchId(long batchId);

    @Query("select COUNT(sp) from StudentProfile sp where sp.batch!=null")
    long countStudentWithBatch();

    @Query("select COUNT(sp) from StudentProfile sp where sp.batch=null")
    long countStudentWithoutBatch();

    @Query("select sp from StudentProfile sp")
    List<StudentProfile> findAll();

}
