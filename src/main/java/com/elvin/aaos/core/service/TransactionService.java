package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.StudentTransactionDetailDto;
import com.elvin.aaos.core.model.dto.StudentTransactionDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {

    StudentTransactionDto save(StudentTransactionDto studentTransactionDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<StudentTransactionDetailDto> listByStudentProfileId(long id);

}
