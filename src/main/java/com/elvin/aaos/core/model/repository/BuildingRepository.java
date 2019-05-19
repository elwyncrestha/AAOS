package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query("SELECT COUNT(b) FROM Building b")
    long countAll();

}
