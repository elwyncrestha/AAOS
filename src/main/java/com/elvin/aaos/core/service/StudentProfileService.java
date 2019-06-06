package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.StudentProfileDto;

public interface StudentProfileService {

    StudentProfileDto getByUserId(long userId);

}
