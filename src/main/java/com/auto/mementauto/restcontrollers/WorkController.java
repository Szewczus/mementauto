package com.auto.mementauto.restcontrollers;

import com.auto.mementauto.dtos.WorkDto;
import com.auto.mementauto.entities.WorkEntity;
import com.auto.mementauto.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkController {
    @Autowired
    WorkService workService;

    @PostMapping("/findWorksById/{id}")
    public ResponseEntity<List<WorkEntity>>findWorksById(@PathVariable Long id){
        return ResponseEntity.ok(workService.findWorksById(id));
    }

    @PostMapping("/findInsurancesById/{id}")
    public ResponseEntity<List<WorkEntity>>findInsurancesById(@PathVariable Long id){
        return ResponseEntity.ok(workService.findInsurancesById(id));
    }

    @PostMapping("/saveWork")
    public ResponseEntity<WorkEntity> saveWork(@RequestBody WorkDto work){
        return ResponseEntity.ok(workService.saveWork(work));
    }

    @PostMapping("/saveInsurance")
    public ResponseEntity<WorkEntity> saveInsurance(@RequestBody WorkDto work){
        return ResponseEntity.ok(workService.saveInsurance(work));
    }
}
