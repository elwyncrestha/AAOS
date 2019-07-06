package com.elvin.aaos.core.model.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.entity.TeacherProfile;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Gender;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class TeacherProfileRepositoryTest extends BaseTest {

    @Autowired
    TeacherProfileRepository teacherProfileRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml",
        "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml"})
    public void testSaveTeacherProfileShouldReturnSavedObject() {
        User user = new User();
        user.setId(2);
        Module module = new Module();
        module.setId(1);
        TeacherProfile teacherProfile = new TeacherProfile(
            "John Doe",
            "john@mail.com",
            new Date(),
            Gender.MALE,
            "Address",
            "9841567845",
            "MIT",
            user,
            module
        );

        TeacherProfile savedTeacherProfile = teacherProfileRepository.save(teacherProfile);
        TeacherProfile teacherProfileByUserId = teacherProfileRepository.findByUserId(2L);
        TeacherProfile teacherProfileById = teacherProfileRepository.findTeacherProfileById(1L);

        assertThat(savedTeacherProfile.getEmail(), equalTo(teacherProfileByUserId.getEmail()));
        assertThat(savedTeacherProfile.getEmail(), equalTo(teacherProfileById.getEmail()));
    }

}
