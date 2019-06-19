package com.elvin.aaos.core.service;

import com.elvin.aaos.core.model.dto.BatchCourseDto;
import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.model.dto.BatchExamDto;
import com.elvin.aaos.core.model.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BatchService {

    long countTotalBatch();

    BatchDto save(BatchDto batchDto, User createdBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    List<BatchDto> list();

    BatchDto getBatch(long id);

    void delete(long id, User deletedBy);

    BatchDto update(BatchDto batchDto, User modifiedBy);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    BatchCourseDto batchWithCourses(long id);

    BatchCourseDto enrollCourses(BatchCourseDto batchCourseDto);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    BatchExamDto batchWithExams(long id);

    BatchExamDto assignExams(BatchExamDto batchExamDto);

}
