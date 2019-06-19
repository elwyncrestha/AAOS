package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Exam findExamByName(String name);

    Exam findExamById(long id);

}
