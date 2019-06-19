package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.NotificationDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationService {

    void save(NotificationDto notificationDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<NotificationDto> getNotificationsByUserId(long userId, long count);

    long count(long userId);

    void markAsRead(long id, User readBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<NotificationDto> getNotificationsByUserId(long userId);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    NotificationDto getById(long id);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<NotificationDto> getAll(long userId);

}
