package com.elvin.aaos.core.model.mapper;

import com.elvin.aaos.core.model.dto.StudentTransactionDetailDto;
import com.elvin.aaos.core.model.entity.StudentTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class StudentTransactionDetailMapper extends BaseMapper<StudentTransaction, StudentTransactionDetailDto> {
}
