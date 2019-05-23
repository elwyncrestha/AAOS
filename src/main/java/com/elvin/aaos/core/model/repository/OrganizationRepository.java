package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findFirstByOrderByIdAsc();

    Organization findOrganizationById(long id);

}
