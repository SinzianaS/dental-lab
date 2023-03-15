package com.dental.lab.data.dao;

import com.dental.lab.filter.JPAFilter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
public abstract class GenericDao<T> {
    private Class<T> classOfData;

    protected EntityManager entityManager;

    /**
     * Constructor for a DAO
     *
     * @param classOfData - The class of the domain object that will be persisted and over which JPA operations will be performed
     *                    The class must be an @Entity
     */
    public GenericDao(Class<T> classOfData, EntityManager entityManager) {
        if (!classOfData.isAnnotationPresent(Entity.class)) {
            throw new PersistenceException("The domain class must be an Entity!");
        }
        this.classOfData = classOfData;
        this.entityManager = entityManager;
    }

    /**
     * Return the entry with the given ID or null if no such entry exists
     *
     * @param id The ID of the object
     * @return The object with the specified ID
     */
    public T find(Object id) {
        return entityManager.find(classOfData, id);
    }

    /**
     * Insert the given object in the database.
     *
     * @param obj The object to store in the database
     * @return The object stored, with the generated values set
     */
    public T persist(T obj) {
        entityManager.persist(obj);
        return obj;
    }

    /**
     * Insert the given object in the database. Same as persist();
     *
     * @param obj The object to store in the database
     * @return The object stored, with the generated values set
     */
    public T insert(T obj) {
        return persist(obj);
    }

    /**
     * Update the entry in the database
     *
     * @param obj The object to update
     * @return The updated object
     */
    public T merge(T obj) {
        entityManager.merge(obj);
        return obj;
    }


    /**
     * Returns all items from the database, with the default limit for the maximum number of results
     *
     * @return The entries from the database
     */
    public List<T> getAll() {
        return get(null);
    }

    /**
     * Return the list of items that satisfy the filter provided. If the filter is null, all items will be returned.
     *
     * @param filter The filter for used for returning the items. Can be null, in which case all items are returned
     * @return The list of items that satisfy the given filter
     */
    public List<T> get(JPAFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classOfData);
        Root<T> root = criteriaQuery.from(classOfData);
        criteriaQuery.select(root);
        if (filter != null) {
            Predicate predicate = filter.getPredicate(criteriaBuilder, root);
            if (predicate != null) {
                criteriaQuery.where(predicate);
            }
            List<Order> orderList = filter.getOrderBy(criteriaBuilder, root);
            if (!orderList.isEmpty()) {
                criteriaQuery.orderBy(orderList);
            }
        }

        TypedQuery query = entityManager.createQuery(criteriaQuery);
        List<T> result = query.getResultList();
        return result;
    }

    /**
        * Hard deletes the object with the given ID from the database, if it exists
        * @param id The ID of the object that will be deleted
        */
       public void delete(String id) {
           T obj = entityManager.find(classOfData, id);
           if (obj != null) {
               entityManager.remove(obj);
           }
       }

}

