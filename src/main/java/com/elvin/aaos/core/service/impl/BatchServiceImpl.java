package com.elvin.aaos.core.service.impl;

import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.model.entity.Batch;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.mapper.BatchMapper;
import com.elvin.aaos.core.model.repository.BatchRepository;
import com.elvin.aaos.core.service.BatchService;
import com.elvin.aaos.web.utility.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public BatchDto getBatch(long id) {
        return batchMapper.mapEntityToDto(batchRepository.findBatchById(id));
    }

    @Override
    public void delete(long id, User deletedBy) {
        Batch batch = batchRepository.findBatchById(id);
        batch.setStatus(Status.DELETED);
        batch.setName(StringConstants.DELETED_BATCH + batch.getId() + "_" + batch.getName());
        batch.setLastModifiedAt(new Date());
        batch.setModifiedBy(deletedBy);
        batchRepository.save(batch);
    }

    @Override
    public BatchDto update(BatchDto batchDto, User modifiedBy) {
        Batch batch = batchRepository.findBatchById(batchDto.getId());
        batch.setName(batchDto.getName());
        batch.setFormationDate(batchDto.getFormationDate());
        batch.setModifiedBy(modifiedBy);
        batch.setLastModifiedAt(new Date());

        return batchMapper.mapEntityToDto(batchRepository.save(batch));
    }
}