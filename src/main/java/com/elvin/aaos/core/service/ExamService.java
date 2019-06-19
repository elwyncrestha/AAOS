package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.ExamDto;
import com.elvin.aaos.core.model.entity.User;

public interface ExamService {

    ExamDto save(ExamDto examDto, User createdBy);

}
