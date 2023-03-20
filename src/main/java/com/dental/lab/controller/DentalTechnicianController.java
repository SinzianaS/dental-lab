package com.dental.lab.controller;

import com.dental.lab.data.domain.DentalTechnician;
import com.dental.lab.service.DentalTechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/technicians")
@Transactional
public class DentalTechnicianController {

    @Autowired
    private DentalTechnicianService dentalTechnicianService;

    @GetMapping("/{id}")
    public ResponseEntity<DentalTechnician> getTechnicianById(@PathVariable UUID id) {
        DentalTechnician technician = dentalTechnicianService.findTechnicianById(id);
        if (technician == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technician, HttpStatus.OK);
    }

    @GetMapping
    public List<DentalTechnician> getAllTechnicians() {
        return dentalTechnicianService.findAllTechnicians();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DentalTechnician> createTechnician(@RequestBody DentalTechnician dentalTechnician) {
        DentalTechnician createdTechnician = dentalTechnicianService.createTechnician(dentalTechnician);
        return new ResponseEntity<>(createdTechnician, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DentalTechnician> updateTechnicianById(@PathVariable("id") UUID id,
                                                                 @RequestBody DentalTechnician updatedDentalTechnician) {
        DentalTechnician technician = dentalTechnicianService.updateTechnician(id, updatedDentalTechnician);
        if (technician == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technician, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDentalTechnician(@PathVariable("id") UUID id) {
        dentalTechnicianService.deleteTechnician(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
