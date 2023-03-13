package com.dental.lab.data.dao;

import com.dental.lab.data.domain.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DentistDao {
    private final EntityManager entityManager;

    @Autowired
    public DentistDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addDentist(Dentist dentistToAdd) {
        entityManager.persist(dentistToAdd);
    }

    public Dentist updateDentist(Dentist dentistToUpdate) {
        return entityManager.merge(dentistToUpdate);
    }

    public List<Dentist> findAllDentists() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dentist> criteriaQuery = criteriaBuilder.createQuery(Dentist.class);
        Root<Dentist> root = criteriaQuery.from(Dentist.class);
        criteriaQuery.select(root);

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
