package com.dental.lab.data.dao;

import com.dental.lab.data.domain.DentalTechnician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DentalTechnicianDao extends GenericDao<DentalTechnician>{

    @Autowired
    public DentalTechnicianDao (EntityManager entityManager) {
        super(DentalTechnician.class, entityManager);
    }

}
