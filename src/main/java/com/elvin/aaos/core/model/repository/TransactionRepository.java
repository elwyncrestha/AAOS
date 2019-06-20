package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.StudentTransaction;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<StudentTransaction, Long> {

    @Query("select st from StudentTransaction st where st.status=?2 and st.studentProfile.id=?1 order by st.transactionDate desc")
    List<StudentTransaction> findAllByStudentProfileId(long studentProfileId, Status status);

    StudentTransaction findStudentTransactionById(long id);

    @Query("select COUNT(st) from StudentTransaction st where st.studentProfile.id=?1 and st.course.id=?2 and st.complete=true")
    long countCompleted(long studentId, long courseId);

}
