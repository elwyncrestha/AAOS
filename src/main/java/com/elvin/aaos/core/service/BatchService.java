package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.model.entity.User;

public interface BatchService {

    long countTotalBatch();

    BatchDto save(BatchDto batchDto, User createdBy);

}
