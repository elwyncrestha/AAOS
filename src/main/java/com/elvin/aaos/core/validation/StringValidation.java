package com.elvin.aaos.core.validation;

import org.springframework.stereotype.Service;

@Service
public class StringValidation {

    public boolean validEmailFormat(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}
