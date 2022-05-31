package com.auto.mementauto.services;

import com.auto.mementauto.dtos.WorkDto;
import com.auto.mementauto.entities.WorkEntity;
import com.auto.mementauto.repositories.VehicleRepository;
import com.auto.mementauto.repositories.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkService {
    @Autowired
    WorkRepository workRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    public List<WorkEntity> findWorksById(Long id){
        List <WorkEntity> workEntityList = workRepository.findWorkEntitiesByVehicleId(id);
        List <WorkEntity> prace = new ArrayList<>();
        for(WorkEntity workEntity : workEntityList){
            if(!workEntity.getCategory().equals("ubezpieczenie")){
                prace.add(workEntity);
            }
        }
        return prace;
    }

    public List<WorkEntity> findInsurancesById(Long id){
        List <WorkEntity> workEntityList = workRepository.findWorkEntitiesByVehicleId(id);
        List <WorkEntity> prace = new ArrayList<>();
        for(WorkEntity workEntity : workEntityList){
            if(workEntity.getCategory().equals("ubezpieczenie")){
                prace.add(workEntity);
            }
        }
        return prace;
    }

    public WorkEntity saveWork(WorkDto work){
        WorkEntity workEntity = new WorkEntity();
        workEntity.setWorkName(work.getWorkName());
        workEntity.setDate(work.getDate());
        workEntity.setHour(work.getHour());
        workEntity.setCategory(work.getCategory());
        workEntity.setVehicle(vehicleRepository.findVehicleEntityById(work.getVehicle()));
        return workRepository.save(workEntity);
    }

    public WorkEntity saveInsurance(WorkDto work){
        WorkEntity workEntity = new WorkEntity();
        workEntity.setWorkName(work.getWorkName());
        workEntity.setDate(work.getDate());
        workEntity.setHour(work.getHour());
        workEntity.setCategory("ubezpieczenie");
        workEntity.setVehicle(vehicleRepository.findVehicleEntityById(work.getVehicle()));
        return workRepository.save(workEntity);
    }
}
