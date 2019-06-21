package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.TroubleShootDto;
import com.elvin.aaos.core.model.entity.User;

public interface TroubleShootService {

    TroubleShootDto save(TroubleShootDto troubleShootDto, User createdBy);

}
