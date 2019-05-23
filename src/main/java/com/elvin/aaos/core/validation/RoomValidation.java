package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.RoomDto;
import com.elvin.aaos.core.model.repository.RoomRepository;
import com.elvin.aaos.web.error.RoomError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomValidation {

    @Autowired
    RoomRepository roomRepository;

    private RoomError roomError = new RoomError();
    private boolean valid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public RoomError saveValidation(RoomDto roomDto) {
        valid = true;

        roomError.setName(checkName(roomDto.getName()));
        roomError.setValid(valid);
        return roomError;
    }

    private String checkName(String name) {
        if (StringUtils.isNotBlank(name)){
            if (roomRepository.findRoomByName(name) != null) {
                logger.debug("ROOM NAME ALREADY EXISTS");
                valid = false;
                roomError.setName("room name already exists");
            }
        } else {
            logger.debug("ROOM NAME CANNOT BE NULL OR EMPTY");
            valid = false;
            return "room name cannot be null or empty";
        }

        return "";
    }

}
