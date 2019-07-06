package com.elvin.aaos.core.model.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.Notification;
import com.elvin.aaos.core.model.entity.Organization;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class OrganizationRepositoryTest extends BaseTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup({"/dataset/user/user-config.xml"})
    public void testSaveOrganizationShouldReturnSavedOrganization() {
        User user = new User();
        user.setId(1L);
        Organization organization = new Organization(
            "Organization 1", new Date(), "Organization Description"
        );

        Organization savedOrganization = organizationRepository.save(organization);
        Organization organizationById = organizationRepository.findOrganizationById(1L);

        assertThat(savedOrganization.getId(), equalTo(organizationById.getId()));
        assertThat(organizationRepository.findFirstByOrderByIdAsc().getId(), equalTo(savedOrganization.getId()));
    }

}
