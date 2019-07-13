package com.elvin.aaos.core.model.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Module;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class ModuleRepositoryTest extends BaseTest {

    @Autowired
    ModuleRepository moduleRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml",
        "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml"})
    public void testCountAllByCourseId() {
        long count = moduleRepository.countAllByCourseId(1L);
        assertThat(count, equalTo(3L));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml",
        "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml"})
    public void testFindModuleByIdAndName() {
        Module moduleById = moduleRepository.findModuleById(1L);
        Module moduleByName = moduleRepository.findModuleByName("Module 1");
        assertThat(moduleById.getId(), equalTo(moduleByName.getId()));
    }

}
