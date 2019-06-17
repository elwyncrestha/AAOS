package com.elvin.aaos.core.validation;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class DateValidation {

    public boolean isPastDate(Date date) {
        return date.before(new Date());
    }

    public boolean isFutureDate(Date date) {
        return date.after(new Date());
    }

    public boolean isValidTime(String time) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            LocalTime localTime = LocalTime.parse(time, dateTimeFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
