package com.dental.lab.data.dao;

import com.dental.lab.data.domain.DentalWork;
import com.dental.lab.data.domain.enums.Color;
import com.dental.lab.data.domain.enums.Status;
import com.dental.lab.data.domain.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DentalWorkDao extends GenericDao<DentalWork> {

    @Autowired
    public DentalWorkDao(EntityManager entityManager) {
        super(DentalWork.class, entityManager);
    }

    /** Returns a list of DentalWorks filtered by the given Status.
    *  @param status the Status to filter the DentalWorks by
    *  @return a list of DentalWorks filtered by the given Status
    */
    public List<DentalWork> findDentalWorksByStatus(Status status) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DentalWork> criteria = builder.createQuery(DentalWork.class);
        Root<DentalWork> root = criteria.from(DentalWork.class);
        criteria.where(builder.equal(root.get("status"), status));
        TypedQuery<DentalWork> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    public List<DentalWork> findDentalWorksByType(Type type) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DentalWork> criteria = builder.createQuery(DentalWork.class);
        Root<DentalWork> root = criteria.from(DentalWork.class);
        criteria.where(builder.equal(root.get("type"), type));
        TypedQuery<DentalWork> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    public List<DentalWork> findDentalWorksByColor(Color color) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DentalWork> criteria = builder.createQuery(DentalWork.class);
        Root<DentalWork> root = criteria.from(DentalWork.class);
        criteria.where(builder.equal(root.get("color"), color));
        TypedQuery<DentalWork> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

}

