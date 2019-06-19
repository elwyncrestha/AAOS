package com.elvin.aaos.core.model.dto;

import com.elvin.aaos.core.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto extends BaseDto<Long> {

    private UserDto user;

    private String title;

    private String description;

    private Status status;

    private String background;

    private String icon;

}
