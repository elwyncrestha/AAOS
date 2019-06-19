package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Notification;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.status=?1 and n.user.id=?2 order by n.createdAt desc")
    List<Notification> findAllByUserId(Status status, long userId, Pageable pageable);

    @Query("select COUNT(n) from Notification n where n.status=?1 and n.user.id=?2")
    long countNotificationForCurrentUser(Status status, long userId);

    Notification findNotificationById(long id);

}
