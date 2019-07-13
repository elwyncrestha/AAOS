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
import com.elvin.aaos.core.model.entity.RoomSchedule;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class RoomScheduleTest extends BaseTest {

    @Autowired
    RoomScheduleRepository roomScheduleRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml", "/dataset/batch/batch-config.xml",
        "/dataset/teacher/teacher-config.xml", "/dataset/building/building-config.xml",
        "/dataset/room/room-config.xml", "/dataset/room/roomSchedule-config.xml"})
    public void testFindRoomSchedulesByStatusShouldReturnList() {
        List<RoomSchedule> roomScheduleList = roomScheduleRepository.findRoomSchedulesByStatus(
            Status.ACTIVE);
        assertThat(roomScheduleList, hasSize(3));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml", "/dataset/batch/batch-config.xml",
        "/dataset/teacher/teacher-config.xml", "/dataset/building/building-config.xml",
        "/dataset/room/room-config.xml", "/dataset/room/roomSchedule-config.xml"})
    public void testFindRoomScheduleByIdShouldReturnRoomSchedule() {
        RoomSchedule roomSchedule = roomScheduleRepository.findRoomScheduleById(1L);
        assertThat(roomSchedule.getId(), equalTo(1L));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml", "/dataset/batch/batch-config.xml",
        "/dataset/teacher/teacher-config.xml", "/dataset/building/building-config.xml",
        "/dataset/room/room-config.xml", "/dataset/room/roomSchedule-config.xml"})
    public void testCountAllByBatchIdShouldReturnValue() {
        long count = roomScheduleRepository.countAllByBatchId(1L);
        assertThat(count, equalTo(2L));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/course/course-config.xml",
        "/dataset/module/module-config.xml", "/dataset/batch/batch-config.xml",
        "/dataset/teacher/teacher-config.xml", "/dataset/building/building-config.xml",
        "/dataset/room/room-config.xml", "/dataset/room/roomSchedule-config.xml"})
    public void testCountAllByRoomIdShouldReturnValue() {
        long count = roomScheduleRepository.countAllByRoomId(2L);
        assertThat(count, equalTo(2L));
    }



}
