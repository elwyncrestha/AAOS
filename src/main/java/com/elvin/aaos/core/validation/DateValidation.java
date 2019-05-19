package com.elvin.aaos.core.validation;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateValidation {

    public boolean isPastDate(Date date) {
        return date.before(new Date());
    }

    public boolean isFutureDate(Date date) {
        return date.after(new Date());
    }

}
