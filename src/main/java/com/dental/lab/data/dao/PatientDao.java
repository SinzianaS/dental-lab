package com.dental.lab.data.dao;

import com.dental.lab.data.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PatientDao extends GenericDao<Patient> {

    @Autowired
    public PatientDao(EntityManager entityManager) {
        super(Patient.class, entityManager);
    }
}
