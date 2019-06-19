package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.NotificationDto;
import com.elvin.aaos.core.model.entity.Notification;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.NotificationMapper;
import com.elvin.aaos.core.model.repository.NotificationRepository;
import com.elvin.aaos.core.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(
            @Autowired NotificationRepository notificationRepository,
            @Autowired NotificationMapper notificationMapper
    ) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void save(NotificationDto notificationDto, User createdBy) {
        Notification notification = notificationMapper.mapDtoToEntity(notificationDto);
        notification.setCreatedBy(createdBy);
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDto> getNotificationsByUserId(long userId, long count) {
        return notificationMapper.mapEntitiesToDtos(notificationRepository.findAllByUserId(Status.ACTIVE, userId, PageRequest.of(0, (int) count)));
    }

    @Override
    public long count(long userId) {
        return notificationRepository.countNotificationForCurrentUser(Status.ACTIVE, userId);
    }

    @Override
    public void markAsRead(long id, User readBy) {
        Notification notification = notificationRepository.findNotificationById(id);
        notification.setStatus(Status.INACTIVE);
        notification.setModifiedBy(readBy);
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDto> getNotificationsByUserId(long userId) {
        return notificationMapper.mapEntitiesToDtos(notificationRepository.findAllByUserId(Status.ACTIVE, userId));
    }

    @Override
    public NotificationDto getById(long id) {
        return notificationMapper.mapEntityToDto(notificationRepository.findNotificationById(id));
    }

    @Override
    public List<NotificationDto> getAll(long userId) {
        return notificationMapper.mapEntitiesToDtos(notificationRepository.findAllByUserId(userId));
    }
}
