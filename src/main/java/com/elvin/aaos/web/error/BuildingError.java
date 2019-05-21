package com.elvin.aaos.web.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingError {

    private boolean isValid;

    private String name;

    private String description;

    private String status;

}
