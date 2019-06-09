package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Batch;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    long countBatchesByStatus(Status status);

    Batch findBatchByName(String name);
    
    Batch findBatchById(long id);

    List<Batch> findBatchesByStatus(Status status);

}
