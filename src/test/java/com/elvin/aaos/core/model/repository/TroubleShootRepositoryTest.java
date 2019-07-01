package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.TroubleShoot;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TroubleShootRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TroubleShootRepository troubleShootRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testSaveShouldSaveProblem() {
        final User user = userRepository.findUserById(2L);
        final TroubleShoot troubleShoot = new TroubleShoot(user, "Problem Description", Status.ACTIVE);
        final TroubleShoot savedProblem = troubleShootRepository.save(troubleShoot);

        final TroubleShoot problem = troubleShootRepository.findTroubleShootById(1L);
        assertThat(savedProblem, equalTo(problem));
    }

}
