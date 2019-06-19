package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.NotificationDto;
import com.elvin.aaos.core.model.entity.User;

import java.util.List;

public interface NotificationService {

    void save(NotificationDto notificationDto, User createdBy);

    List<NotificationDto> getNotificationsByUserId(long userId, long count);

    long count(long userId);

    void markAsRead(long id, User readBy);

}
