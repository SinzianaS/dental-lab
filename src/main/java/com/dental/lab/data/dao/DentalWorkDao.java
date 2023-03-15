package com.dental.lab.data.dao;

import com.dental.lab.data.domain.DentalWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DentalWorkDao extends GenericDao<DentalWork>{

    @Autowired
    public DentalWorkDao(EntityManager entityManager) {
        super(DentalWork.class, entityManager);
    }
}

