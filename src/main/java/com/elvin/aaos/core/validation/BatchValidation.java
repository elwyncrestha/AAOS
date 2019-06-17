package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.model.entity.Batch;
import com.elvin.aaos.core.model.repository.BatchRepository;
import com.elvin.aaos.web.error.BatchError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchValidation {

    private final BatchRepository batchRepository;
    private BatchError batchError = new BatchError();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean valid = true;

    public BatchValidation(
            @Autowired BatchRepository batchRepository
    ) {
        this.batchRepository = batchRepository;
    }

    public BatchError saveValidation(BatchDto batchDto) {

        valid = true;

        if (StringUtils.isEmpty(batchDto.getName())) {
            batchError.setName("batch name cannot be empty");
            logger.debug("BATCH NAME CANNOT BE EMPTY");
            valid = false;
        }
        else if (batchRepository.findBatchByName(batchDto.getName()) != null) {
            batchError.setName("batch already exists");
            logger.debug("BATCH ALREADY EXISTS");
            valid = false;
        }

        batchError.setValid(valid);
        return batchError;
    }

    public BatchError updateValidation(BatchDto batchDto) {

        Batch batch = batchRepository.findBatchById(batchDto.getId());
        valid = true;

        if (StringUtils.isEmpty(batchDto.getName())) {
            batchError.setName("batch name cannot be empty");
            logger.debug("BATCH NAME CANNOT BE EMPTY");
            valid = false;
        }
        else if (!batchDto.getName().equals(batch.getName())) {
            if (batchRepository.findBatchByName(batchDto.getName()) != null) {
                batchError.setName("batch already exists");
                logger.debug("BATCH ALREADY EXISTS");
                valid = false;
            }
        }

        batchError.setValid(valid);
        return batchError;

    }

}
