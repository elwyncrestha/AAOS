package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Building;
import com.elvin.aaos.core.model.enums.BuildingStatus;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class BuildingRepositoryTest extends BaseTest {

    @Autowired
    BuildingRepository buildingRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/building/building-config.xml"})
    public void testFindBuildingByIdShouldReturnBuilding() {
        final Building building = buildingRepository.findBuildingById(1L);
        assertThat(building.getId(), equalTo(1L));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/building/building-config.xml"})
    public void testFindByStatusExceptShouldReturnValue() {
        final List<Building> buildings = buildingRepository.findByStatusExcept(BuildingStatus.DEMOLISHED);
        assertThat(buildings, hasSize(3));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/building/building-config.xml"})
    public void testFindBuildingByNameShouldReturnBuilding() {
        String name = "Building 1";
        final Building building = buildingRepository.findBuildingByName(name);
        assertThat(building.getName(), equalTo(name));
    }

}
