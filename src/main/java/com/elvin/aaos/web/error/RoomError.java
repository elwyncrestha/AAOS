package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomError {

    private boolean isValid;

    private String name;

    private String roomType;

    private String building;

}
