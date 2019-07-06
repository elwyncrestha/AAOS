package com.elvin.aaos.core.model.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.entity.StudentProfile;
import com.elvin.aaos.core.model.entity.StudentReport;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class StudentReportRepositoryTest extends BaseTest {

    @Autowired
    StudentReportRepository studentReportRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml", "/dataset/batch/batch-config.xml",
        "/dataset/studentProfile/studentProfile-config.xml"})
    public void testSaveStudentReportShouldSaveObject() {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(1L);
        Module module = new Module();
        module.setId(1L);
        StudentReport studentReport = new StudentReport(
            module, studentProfile, 90.5, Status.ACTIVE
        );
        StudentReport savedReport = studentReportRepository.save(studentReport);
        List<StudentReport> studentReportsByStudentId = studentReportRepository
            .findStudentReportsByStudentId(1L, Status.ACTIVE);
        StudentReport studentReportById = studentReportRepository.findStudentReportById(1L);

        assertThat(studentReportsByStudentId, hasSize(1));
        assertThat(studentReportById.getMarksObtained(), equalTo(savedReport.getMarksObtained()));
    }
}
