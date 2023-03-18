package com.dental.lab.service;


import com.dental.lab.data.dao.DentistDao;
import com.dental.lab.data.domain.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing dentists.
 * <p>
 * This class provides methods for creating, reading, updating, and deleting
 * dentist entities, as well as querying for dentists by address or clinic name.
 * <p>
 * The service methods ensure that all modifications to the entity are made
 * within a transactional context.
 */

@Service
public class DentistService {
    private final DentistDao dentistDao;

    @Autowired
    public DentistService(DentistDao dentistDao) {
        this.dentistDao = dentistDao;
    }

    public Dentist getDentistById(UUID id) {
        return dentistDao.find(id);
    }

    @Transactional
    public Dentist createDentist(Dentist dentist) {
        return dentistDao.merge(dentist);
    }

    public Dentist updateDentist(UUID id, Dentist dentist) {
        return dentistDao.merge(dentist);
    }


    public void deleteDentist(UUID id) {
        dentistDao.delete(id);
    }

    public List<Dentist> getAllDentists() {
        return dentistDao.getAll();
    }

    public List<Dentist> getDentistsByAddress(String address) {
        return dentistDao.getByAddress(address);
    }

    public List<Dentist> getDentistsByClinicName(String clinic) {
        return dentistDao.findDentistsByClinicName(clinic);
    }
}
