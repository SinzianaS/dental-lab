package com.dental.lab.data.dao;

import com.dental.lab.data.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PatientDao {
    /**
     * inject the Entity Manager
     * This will offer us the needed functionality to build our DAO on top of Hibernate and JPA.
     */
    private final EntityManager entityManager;

    @Autowired
    public PatientDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * We call persist(), merge() from EntityManager API
     * all database operations are done through the entity manager
     */
    public void addPatient(Patient patientToAdd) {
        entityManager.persist(patientToAdd);
    }

    public Patient updatePatient(Patient toUpdate) {
        return entityManager.merge(toUpdate);
    }

    /**
     * JPA has a class called CriteriaBuilder which is used to build criteria for retrieving data.
     * we build a CriteriaQuery, which will do a simple select()
     *  we retrieve the CriteriaBuilder from our entity manager, and using it we will be constructing a CriteriaQuery
     *  The CriteriaQuery dictates the type of the returned object.
     *  we need to provide the FROM clause as well, which is also the Patient class.
     *  The FROM clause is equivalent to creating our Root object.
     *  we do a select() using the CriteriaQuery.
     *  we need to translate the query to a TypedQuery, which gets executed to return the data.
     */

    public List<Patient> findAllPatients() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> root = criteriaQuery.from(Patient.class);
        criteriaQuery.select(root);

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
