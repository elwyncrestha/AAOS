package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("select m from Module m where m.course.id=?1")
    List<Module> findAllByCourseId(long batchId);

}
