package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.StudentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<StudentTransaction, Long> {



}
