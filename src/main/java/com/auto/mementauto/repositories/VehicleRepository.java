package com.auto.mementauto.repositories;

import com.auto.mementauto.entities.UserEntity;
import com.auto.mementauto.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    public List<VehicleEntity> findVehicleEntitiesByOwner(UserEntity userEntity);
    public VehicleEntity findVehicleEntityById(Long id);

}
