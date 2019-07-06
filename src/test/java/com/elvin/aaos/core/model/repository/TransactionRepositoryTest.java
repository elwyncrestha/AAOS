package com.elvin.aaos.core.model.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.entity.StudentProfile;
import com.elvin.aaos.core.model.entity.StudentTransaction;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class TransactionRepositoryTest extends BaseTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml",
        "/dataset/batch/batch-config.xml", "/dataset/studentProfile/studentProfile-config.xml"})
    public void testSaveTransactionMustReturnSavedTransaction() {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(1);
        Course course = new Course();
        course.setId(1);
        StudentTransaction studentTransaction = new StudentTransaction(
            studentProfile, course, true, "Remarks", new Date(), Status.ACTIVE
        );
        StudentTransaction savedTransaction = transactionRepository.save(studentTransaction);

        StudentTransaction studentTransactionById = transactionRepository.findStudentTransactionById(1L);
        List<StudentTransaction> studentTransactions = transactionRepository.findAllByStudentProfileId(1, Status.ACTIVE);

        assertThat(savedTransaction.getRemarks(), equalTo(studentTransactionById.getRemarks()));
        assertThat(studentTransactions, hasSize(1));
    }

}
