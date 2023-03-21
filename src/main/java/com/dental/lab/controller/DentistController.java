package com.dental.lab.controller;

import com.dental.lab.data.domain.Dentist;
import com.dental.lab.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dentists")
@CrossOrigin(origins = "http://localhost:3000")
@Transactional
public class DentistController {

    private final DentistService dentistService;

    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable("id") UUID id) {
        Dentist dentist = dentistService.getDentistById(id);
        if (dentist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dentist, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist dentist) {
        Dentist createdDentist = dentistService.createDentist(dentist);
        return new ResponseEntity<>(createdDentist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Dentist> updateDentistById(@PathVariable("id") UUID id, @RequestBody Dentist updatedDentist) {
        Dentist dentist = dentistService.updateDentist(id, updatedDentist);
        if (dentist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dentist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDentist(@PathVariable("id") UUID id) {
        dentistService.deleteDentist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> getAllDentists() {
        List<Dentist> dentists = dentistService.getAllDentists();
        return new ResponseEntity<>(dentists, HttpStatus.OK);
    }

    @GetMapping(params = "address")
    public ResponseEntity<List<Dentist>> getDentistsByAddress(@RequestParam("address") String address) {
        List<Dentist> dentists = dentistService.getDentistsByAddress(address);
        if (dentists == null || dentists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dentists, HttpStatus.OK);
    }

    @GetMapping(params = "clinic")
    public ResponseEntity<List<Dentist>> getDentistsByClinicName(@RequestParam("clinic") String clinicName) {
        List<Dentist> dentists = dentistService.getDentistsByClinicName(clinicName);
        if (dentists == null || dentists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dentists, HttpStatus.OK);
    }

}
