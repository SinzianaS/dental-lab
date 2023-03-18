package com.dental.lab.service;

import com.dental.lab.data.dao.DentalTechnicianDao;
import com.dental.lab.data.domain.DentalTechnician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DentalTechnicianService {

    @Autowired
    private DentalTechnicianDao dentalTechnicianDao;

    public DentalTechnician findTechnicianById(UUID id) {
        return dentalTechnicianDao.find(id);
    }

    public List<DentalTechnician> findAllTechnicians() {
        return dentalTechnicianDao.getAll();
    }

    public DentalTechnician createTechnician(DentalTechnician dentalTechnician) {
        return dentalTechnicianDao.merge(dentalTechnician);
    }

    public DentalTechnician updateTechnician(UUID id,DentalTechnician dentalTechnician) {
        return dentalTechnicianDao.merge(dentalTechnician);
    }

    public void deleteTechnician(UUID id) {
        dentalTechnicianDao.delete(id);
    }
}
