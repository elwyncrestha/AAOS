package com.elvin.aaos.core.model.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Exam;
import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class ExamRepositoryTest extends BaseTest {

    @Autowired
    ExamRepository examRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml",
        "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml"})
    public void testSaveShouldReturnSavedExam() {
        String name = "Exam 1";
        Module module = new Module();
        module.setId(1);
        Exam exam = new Exam(name, new Date(), new Date(), new Date(), new Date(), module, Status.ACTIVE);
        exam.setId(1);
        Exam savedExam = examRepository.save(exam);
        Exam examById = examRepository.findExamById(1L);
        Exam examByName = examRepository.findExamByName(name);

        assertThat(savedExam.getId(), equalTo(examById.getId()));
        assertThat(savedExam.getId(), equalTo(examByName.getId()));
    }

}
