package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.StudentTransactionDto;
import com.elvin.aaos.core.model.entity.StudentTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class StudentTransactionMapper extends BaseMapper<StudentTransaction, StudentTransactionDto> {
}
