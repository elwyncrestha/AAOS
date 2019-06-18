package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.StudentTransactionDto;
import com.elvin.aaos.core.model.entity.User;

public interface TransactionService {

    StudentTransactionDto save(StudentTransactionDto studentTransactionDto, User createdBy);

}
