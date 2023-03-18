package com.dental.lab.service;

import com.dental.lab.data.dao.PatientDao;
import com.dental.lab.data.domain.Patient;
import com.dental.lab.filter.JPAFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public Patient getPatientById(UUID id) {
        return patientDao.find(id);
    }

    public List<Patient> getAllPatients() {
        return patientDao.getAll();
    }

    public List<Patient> getPatientsByDentist(String name) {
        JPAFilter filter = new JPAFilter() {
            @Override
            public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root root) {
                Join<Object, Object> join = root.join("dentist");
                return criteriaBuilder.like(
                        criteriaBuilder.lower(join.get("name")), "%" + name.toLowerCase() + "%"
                );
            }
        };
        return patientDao.get(filter);
    }

    @Transactional
    public Patient createPatient(Patient patient) {
        return patientDao.merge(patient);
    }

    @Transactional
    public Patient updatePatient(Patient patient) {
        return patientDao.merge(patient);
    }

    @Transactional
    public void deletePatient(UUID id) {
        patientDao.delete(id);
    }
}
