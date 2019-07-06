package com.elvin.aaos.core.model.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Batch;
import com.elvin.aaos.core.model.entity.StudentProfile;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Gender;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class StudentProfileRepositoryTest extends BaseTest {

    @Autowired
    StudentProfileRepository studentProfileRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/batch/batch-config.xml"})
    public void testSaveStudentProfileShouldReturnSavedObject() {
        User user = new User();
        user.setId(3);
        Batch batch = new Batch();
        batch.setId(1);
        StudentProfile studentProfile = new StudentProfile(
            "Elvin Shrestha",
            "elwyncrestha@gmail.com",
            new Date(),
            Gender.MALE,
            "Jhochhen",
            "015123037",
            "9860687788",
            "9841037639",
            "parent@gmail.com",
            "Mother name",
            "9841326545",
            "Father Name",
            "9865457898",
            user,
            batch
        );

        StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);
        StudentProfile studentProfileByUserId = studentProfileRepository.findByUserId(3L);
        StudentProfile studentProfileById = studentProfileRepository.findStudentProfileById(1L);

        assertThat(savedStudentProfile.getEmail(), equalTo(studentProfileByUserId.getEmail()));
        assertThat(savedStudentProfile.getEmail(), equalTo(studentProfileById.getEmail()));
    }

}
