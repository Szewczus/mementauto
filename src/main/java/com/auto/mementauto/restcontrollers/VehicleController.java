package com.auto.mementauto.restcontrollers;

import com.auto.mementauto.dtos.VehicleDto;
import com.auto.mementauto.entities.VehicleEntity;
import com.auto.mementauto.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @PostMapping("/vehicles")
    public ResponseEntity<List<VehicleEntity>> showUserVehicles(){
        return ResponseEntity.ok(vehicleService.showUserVehicles());
    }

    @PostMapping("/saveVehicle")
    public ResponseEntity<VehicleEntity> saveVehicle(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.ok(vehicleService.saveVahicle(vehicleDto));
    }

    @PostMapping("/changeOwner")
    public ResponseEntity<VehicleEntity> saveVahicleAndOwner(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.ok(vehicleService.changeOwner(vehicleDto));
    }

    @PostMapping("/showVehicle/{id}")
    public ResponseEntity<VehicleEntity>showVehicle(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.showVehicleById(id));
    }

    @PostMapping("/editVehicle")
    public ResponseEntity<VehicleEntity> editVehicle(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.ok(vehicleService.editVehicle(vehicleDto));
    }

}
