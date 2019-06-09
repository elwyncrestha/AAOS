package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BatchService {

    long countTotalBatch();

    BatchDto save(BatchDto batchDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<BatchDto> list();

}
