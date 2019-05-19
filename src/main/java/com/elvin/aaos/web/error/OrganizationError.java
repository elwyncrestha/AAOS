package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationError {

    private boolean isValid;

    private String name;

    private String establishment;

    private String description;

}
