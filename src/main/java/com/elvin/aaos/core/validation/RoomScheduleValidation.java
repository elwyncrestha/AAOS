package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.RoomScheduleDto;
import com.elvin.aaos.core.model.repository.RoomScheduleRepository;
import com.elvin.aaos.web.error.RoomScheduleError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomScheduleValidation {

    private final RoomScheduleRepository roomScheduleRepository;
    private final DateValidation dateValidation;
    private RoomScheduleError roomScheduleError = new RoomScheduleError();
    private boolean valid = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public RoomScheduleValidation(
            @Autowired RoomScheduleRepository roomScheduleRepository,
            @Autowired DateValidation dateValidation
    ) {
        this.roomScheduleRepository = roomScheduleRepository;
        this.dateValidation = dateValidation;
    }

    public RoomScheduleError saveValidation(RoomScheduleDto roomScheduleDto) {
        valid = true;

        roomScheduleError.setStartTime(checkTime(roomScheduleDto.getStrStartTime(), "start time"));
        roomScheduleError.setEndTime(checkTime(roomScheduleDto.getStrEndTime(), "end time"));
        roomScheduleError.setValid(valid);
        return roomScheduleError;
    }

    private String checkTime(String time, String target) {
        if (StringUtils.isNotBlank(time)){
            if (!dateValidation.isValidTime(time)) {
                logger.debug("INVALID " + target.toUpperCase());
                valid = false;
                return "invalid " + target;
            }
        } else {
            logger.debug(target.toUpperCase() + " CANNOT BE NULL OR EMPTY");
            valid = false;
            return target + " cannot be null or empty";
        }

        return "";
    }

}
