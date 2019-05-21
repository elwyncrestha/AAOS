package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Building;
import com.elvin.aaos.core.model.enums.BuildingStatus;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query("SELECT COUNT(b) FROM Building b")
    long countAll();

    Building findBuildingById(long id);

    @Query("SELECT b FROM Building b where b.status!=?1")
    List<Building> findByStatusExcept(BuildingStatus buildingStatus);

    Building findBuildingByName(String name);

}
