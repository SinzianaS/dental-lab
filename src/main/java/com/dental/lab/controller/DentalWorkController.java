package com.dental.lab.controller;

import com.dental.lab.data.domain.DentalWork;
import com.dental.lab.data.domain.Dentist;
import com.dental.lab.data.domain.enums.Color;
import com.dental.lab.data.domain.enums.Status;
import com.dental.lab.data.domain.enums.Type;
import com.dental.lab.service.DentalWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/dental-works")
@CrossOrigin(origins = "http://localhost:3000")
@Transactional
public class DentalWorkController {

    private final DentalWorkService dentalWorkService;

    @GetMapping("/all")
    public List<DentalWork> getAllWorks() {
        return dentalWorkService.getAllDentalWorks();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DentalWork> getDentalWorkById(
            @PathVariable UUID id,
            @RequestParam(name = "status", required = false) Status status) {
        DentalWork dentalWork = dentalWorkService.getDentalWorkById(id);
        if (dentalWork != null) {
            return ResponseEntity.ok(dentalWork);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> saveDentalWork(@RequestBody DentalWork dentalWork) {
        dentalWorkService.saveDentalWork(dentalWork);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dentalWork.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDentalWorkById(@PathVariable UUID id) {
        dentalWorkService.deleteDentalWorkById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getDentalWorksByStatus(@PathVariable("status") String statusString) {
        Status status = Status.getByName(statusString.toLowerCase());
        List<DentalWork> dentalWorks = dentalWorkService.getDentalWorksByStatus(status.getByName(statusString));
        if (dentalWorks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dentalWorks);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getDentalWorksByType(@PathVariable("type") String typeString) {
        Type type = Type.getByName(typeString.toLowerCase());
        List<DentalWork> dentalWorks = dentalWorkService.getDentalWorksByType(type.getByName(typeString));
        if (dentalWorks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dentalWorks);
    }


    @GetMapping("/color/{color}")
    public ResponseEntity<?> getDentalWorksByColor(@PathVariable("color") String colorString) {
        Color color = Color.getByName(colorString.toLowerCase());
        List<DentalWork> dentalWorks = dentalWorkService.getDentalWorksByColor(color.getByName(colorString));
        if (dentalWorks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dentalWorks);
    }


    @PutMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<DentalWork> updateDentalWorkById(@PathVariable("id") UUID id,
                                                           @RequestBody DentalWork updatedDentalWork) {
        DentalWork dentalWork = dentalWorkService.updateDentalWork(id, updatedDentalWork);
        if (dentalWork == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dentalWork, HttpStatus.OK);
    }
}


