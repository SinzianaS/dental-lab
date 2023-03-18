package com.dental.lab.data.dao;

import com.dental.lab.data.domain.DentalWork;
import com.dental.lab.data.domain.Dentist;
import com.dental.lab.data.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DentistDao extends GenericDao<Dentist> {
    @Autowired
    public DentistDao(EntityManager entityManager) {
        super(Dentist.class, entityManager);
    }

    public List<Dentist> findDentistByAddress(String address) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dentist> criteria = builder.createQuery(Dentist.class);
        Root<Dentist> root = criteria.from(Dentist.class);
        criteria.where(builder.equal(root.get("address"), address));
        TypedQuery<Dentist> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }
}
