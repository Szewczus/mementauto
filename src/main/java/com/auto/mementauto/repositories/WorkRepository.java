package com.auto.mementauto.repositories;

import com.auto.mementauto.entities.WorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Long> {
    public List<WorkEntity> findWorkEntitiesByVehicleId(Long id);
}
