package com.elvin.aaos.core.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseDto<PK> {

    private PK id;

    private Date createdAt = new Date();

    private Date lastModifiedAt = new Date();

    private int version;

}
