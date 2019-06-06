package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.TeacherProfileDto;

public interface TeacherProfileService {

    TeacherProfileDto getByUserId(long userId);

}
