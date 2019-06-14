package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Module;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("select m from Module m where m.course.id=?1")
    List<Module> findAllByCourseId(long batchId);

    long countModulesByStatus(Status status);

    Module findModuleById(long id);

    Module findModuleByName(String name);

    List<Module> findModulesByStatus(Status status);

}
