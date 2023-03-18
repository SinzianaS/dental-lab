package com.dental.lab.data.dao;

import com.dental.lab.data.domain.Dentist;
import com.dental.lab.filter.JPAFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class DentistDao extends GenericDao<Dentist> {
    @Autowired
    public DentistDao(EntityManager entityManager) {
        super(Dentist.class, entityManager);
    }

    public List<Dentist> findDentistsByClinicName(String clinicName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dentist> query = cb.createQuery(Dentist.class);
        Root<Dentist> root = query.from(Dentist.class);
        query.select(root);


        String clinicNameLowerCase = clinicName.toLowerCase();
        query.where(cb.like(cb.lower(root.get("clinicName")), "%" + clinicNameLowerCase + "%"));

        TypedQuery<Dentist> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    public List<Dentist> getByAddress(String address) {
        String lowercaseAddress = address.toLowerCase();
        JPAFilter filter = new JPAFilter() {
            @Override
            public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root root) {
                Expression<String> addressExpression = criteriaBuilder.lower(root.get("address"));
                return criteriaBuilder.equal(addressExpression, lowercaseAddress);
            }
        };
        return get(filter);
    }
}

