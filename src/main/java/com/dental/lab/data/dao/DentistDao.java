package com.dental.lab.data.dao;

import com.dental.lab.data.domain.Dentist;
import com.dental.lab.filter.JPAFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class DentistDao extends GenericDao<Dentist> {
    @Autowired
    public DentistDao(EntityManager entityManager) {
        super(Dentist.class, entityManager);
    }

    public List<Dentist> getDentistsByClinicName(String clinicName) {

        JPAFilter filter = new JPAFilter() {
            @Override
            public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root root) {
                Expression<String> lowerCaseClinicName = criteriaBuilder.lower(root.get("clinicName"));
                return criteriaBuilder.like(lowerCaseClinicName, "%" + clinicName.toLowerCase() + "%");
            }
        };
        return get(filter);
    }


    public List<Dentist> getByAddress(String address) {
        JPAFilter filter = new JPAFilter() {
            @Override
            public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root root) {
                Expression<String> lowerCaseAddress = criteriaBuilder.lower(root.get("address"));
                return criteriaBuilder.like(lowerCaseAddress, "%" + address.toLowerCase() + "%");
            }
        };
        return get(filter);
    }
}

