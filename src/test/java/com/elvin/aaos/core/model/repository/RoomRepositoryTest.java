package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Building;
import com.elvin.aaos.core.model.entity.Room;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoomRepositoryTest extends BaseTest {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/building/building-config.xml"})
    public void testSaveShouldSaveRoom() {
        final Building building = buildingRepository.findBuildingById(1L);
        final User user = userRepository.findUserById(1L);
        final Room room = new Room("Room 1", RoomType.LECTURE_ROOM, Status.ACTIVE, building);
        room.setCreatedAt(new Date());
        room.setCreatedBy(user);
        Room savedRoom = roomRepository.save(room);

        final Room getRoom = roomRepository.findRoomById(1L);
        assertThat(savedRoom.getName(), equalTo(getRoom.getName()));
    }

}
