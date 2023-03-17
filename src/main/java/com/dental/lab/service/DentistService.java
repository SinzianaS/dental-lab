package com.dental.lab.service;

import com.dental.lab.model.Dentist;
import com.dental.lab.repository.DentistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DentistService {
    private final DentistRepository dentistRepository;

    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    public Dentist saveDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    public Dentist getDentistById(UUID id) {
        return dentistRepository.findById(id).get();
    }

    public List<Dentist> getDentistByName(String name) {
        return dentistRepository.findByName(name);
    }

    public Dentist updateDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    public void deleteDentistById(UUID id) {
        dentistRepository.deleteById(id);
    }
}

