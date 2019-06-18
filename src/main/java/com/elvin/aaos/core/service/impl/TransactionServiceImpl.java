package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.StudentTransactionDto;
import com.elvin.aaos.core.model.entity.Course;
import com.elvin.aaos.core.model.entity.StudentProfile;
import com.elvin.aaos.core.model.entity.StudentTransaction;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.mapper.StudentTransactionMapper;
import com.elvin.aaos.core.model.repository.TransactionRepository;
import com.elvin.aaos.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final StudentTransactionMapper studentTransactionMapper;

    public TransactionServiceImpl(
            @Autowired TransactionRepository transactionRepository,
            @Autowired StudentTransactionMapper studentTransactionMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.studentTransactionMapper = studentTransactionMapper;
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

        return studentTransactionMapper.mapEntityToDto(transactionRepository.save(studentTransaction));
    }
}
