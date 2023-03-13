package com.dental.lab.data.dao;

import com.dental.lab.data.domain.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DentistDao extends GenericDao<Dentist>{
    @Autowired
      public DentistDao(EntityManager entityManager) {
          super(Dentist.class, entityManager);
      }
}
