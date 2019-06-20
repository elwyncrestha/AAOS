package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.StudentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentReportRepository extends JpaRepository<StudentReport, Long> {

    @Query("select COUNT(sr) from StudentReport sr where sr.studentProfile.id=?1 and sr.module.id=?2")
    long count(long studentProfileId, long moduleId);

}
