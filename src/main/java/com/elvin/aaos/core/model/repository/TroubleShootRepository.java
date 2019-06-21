package com.elvin.aaos.core.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elvin.aaos.core.model.entity.TroubleShoot;
import com.elvin.aaos.core.model.enums.Status;

public interface TroubleShootRepository extends JpaRepository<TroubleShoot, Long> {

    List<TroubleShoot> findAllByStatus(Status status);

    TroubleShoot findTroubleShootById(long id);

}
