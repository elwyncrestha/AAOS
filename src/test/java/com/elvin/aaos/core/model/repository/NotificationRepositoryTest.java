package com.elvin.aaos.core.model.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Notification;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class NotificationRepositoryTest extends BaseTest {

    @Autowired
    NotificationRepository notificationRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml"})
    public void testSaveNotificationShouldReturnSavedNotification() {
        User user = new User();
        user.setId(1L);
        Notification notification = new Notification(
            user, "Notification 1", "Notification Description", Status.ACTIVE, "bg-primary", "fa-user"
        );
        Notification savedNotification = notificationRepository.save(notification);
        Notification notificationById = notificationRepository.findNotificationById(1L);

        assertThat(savedNotification.getId(), equalTo(notificationById.getId()));
        assertThat(notificationRepository.findAllByUserId(1L), hasSize(1));
    }

}
