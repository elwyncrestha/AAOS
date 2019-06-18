package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.StudentTransactionDetailDto;
import com.elvin.aaos.core.model.dto.StudentTransactionDto;
import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.entity.StudentProfile;
import com.elvin.aaos.core.model.entity.StudentTransaction;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.StudentTransactionDetailMapper;
import com.elvin.aaos.core.model.mapper.StudentTransactionMapper;
import com.elvin.aaos.core.model.repository.TransactionRepository;
import com.elvin.aaos.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final StudentTransactionMapper studentTransactionMapper;
    private final StudentTransactionDetailMapper studentTransactionDetailMapper;

    public TransactionServiceImpl(
            @Autowired TransactionRepository transactionRepository,
            @Autowired StudentTransactionMapper studentTransactionMapper,
            @Autowired StudentTransactionDetailMapper studentTransactionDetailMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.studentTransactionMapper = studentTransactionMapper;
        this.studentTransactionDetailMapper = studentTransactionDetailMapper;
    }

    @Override
    public StudentTransactionDto save(StudentTransactionDto studentTransactionDto, User createdBy) {
        StudentTransaction studentTransaction = studentTransactionMapper.mapDtoToEntity(studentTransactionDto);
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(studentTransactionDto.getStudentProfileId());
        studentTransaction.setStudentProfile(studentProfile);
        Course course = new Course();
        course.setId(studentTransactionDto.getCourseId());
        studentTransaction.setCourse(course);
        studentTransaction.setCreatedBy(createdBy);
        studentTransaction.setStatus(Status.ACTIVE);

        return studentTransactionMapper.mapEntityToDto(transactionRepository.save(studentTransaction));
    }

    @Override
    public List<StudentTransactionDetailDto> listByStudentProfileId(long id) {
        return studentTransactionDetailMapper.mapEntitiesToDtos(transactionRepository.findAllByStudentProfileId(id, Status.ACTIVE));
    }
}
