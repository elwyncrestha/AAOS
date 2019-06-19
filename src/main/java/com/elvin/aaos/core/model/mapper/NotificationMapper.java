package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.NotificationDto;
import com.elvin.aaos.core.model.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class NotificationMapper extends BaseMapper<Notification, NotificationDto> {
}
