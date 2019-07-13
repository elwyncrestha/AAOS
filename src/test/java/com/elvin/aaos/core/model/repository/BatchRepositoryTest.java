package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Batch;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class BatchRepositoryTest extends BaseTest {

    @Autowired
    BatchRepository batchRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/batch/batch-config.xml"})
    public void testCountBatchesByStatusShouldReturnValue() {
        long count = batchRepository.countBatchesByStatus(Status.ACTIVE);
        assertThat(count, equalTo(3L));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/batch/batch-config.xml"})
    public void testFindBatchByName() {
        String name = "Batch 1";
        Batch batch = batchRepository.findBatchByName(name);
        assertThat(batch.getName(), equalTo(name));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/batch/batch-config.xml"})
    public void testFindBatchById() {
        long id = 1L;
        Batch batch = batchRepository.findBatchById(id);
        assertThat(batch.getId(), equalTo(id));
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml", "/dataset/batch/batch-config.xml"})
    public void testFindBatchesByStatus() {
        List<Batch> batchList = batchRepository.findBatchesByStatus(Status.ACTIVE);
        assertThat(batchList, hasSize(3));
    }

}
