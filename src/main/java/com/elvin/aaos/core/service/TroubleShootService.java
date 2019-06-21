package com.elvin.aaos.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elvin.aaos.core.model.dto.TroubleShootDto;
import com.elvin.aaos.core.model.entity.User;

public interface TroubleShootService {

    TroubleShootDto save(TroubleShootDto troubleShootDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<TroubleShootDto> list();

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    TroubleShootDto getById(long id);

    void delete(long id, User deletedBy);

}
