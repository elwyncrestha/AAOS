package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.model.entity.Batch;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.BatchMapper;
import com.elvin.aaos.core.model.repository.BatchRepository;
import com.elvin.aaos.core.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final BatchMapper batchMapper;

    public BatchServiceImpl(
            @Autowired BatchRepository batchRepository,
            @Autowired BatchMapper batchMapper
    ) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
    }

    @Override
    public long countTotalBatch() {
        return batchRepository.countBatchesByStatus(Status.ACTIVE);
    }

    @Override
    public BatchDto save(BatchDto batchDto, User createdBy) {
        Batch batch = batchMapper.mapDtoToEntity(batchDto);
        batch.setStatus(Status.ACTIVE);
        batch.setCreatedBy(createdBy);

        return batchMapper.mapEntityToDto(batchRepository.save(batch));
    }

    @Override
    public List<BatchDto> list() {
        return batchMapper.mapEntitiesToDtos(batchRepository.findBatchesByStatus(Status.ACTIVE));
    }
}
