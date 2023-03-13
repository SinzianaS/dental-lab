package com.dental.lab.data.dao;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public abstract class GenericDao<T> {
    private Class<T> classOfData;

    protected EntityManager entityManager;

    /**
     * Constructor for a DAO
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
     * @param id The ID of the object
     * @return The object with the specified ID
     */
    public T find(Object id) {
        return entityManager.find(classOfData, id);
    }

    /**
     * Insert the given object in the database.
     * @param obj The object to store in the database
     * @return The object stored, with the generated values set
     */
    public T persist(T obj) {
        entityManager.persist(obj);
        return obj;
    }

    /**
     * Insert the given object in the database. Same as persist();
     * @param obj The object to store in the database
     * @return The object stored, with the generated values set
     */
    public T insert(T obj) {
        return persist(obj);
    }

    /**
     * Update the entry in the database
     * @param obj The object to update
     * @return The updated object
     */
    public T merge(T obj) {
        entityManager.merge(obj);
        return obj;
    }
}

