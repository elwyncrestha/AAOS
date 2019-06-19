package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Exam;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Exam findExamByName(String name);

    Exam findExamById(long id);

    @Query("select e from Exam e where e.status=?1 order by e.start desc")
    List<Exam> findExamsByStatus(Status status);

}
