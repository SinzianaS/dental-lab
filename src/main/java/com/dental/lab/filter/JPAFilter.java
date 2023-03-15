package com.dental.lab.filter;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class JPAFilter {

    protected List<String> orderBy = new ArrayList<>();

    /**
     * Return the JPA Predicate which filters the result. This can be obtained using the helper methods from this class
     *
     * @param criteriaBuilder The CriteriaBuilder used for building the predicate
     * @param root            The JPA Root used for building the predicate
     * @return The Predicate that was build and will be used by the DAO to filter the result
     */
    public abstract Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root root);

    /**
     * Order the result by the specified filed. Can be chained to order by multiple fields
     *
     * @param field The field name to order by
     * @return The current JPA Filter
     */
    public JPAFilter orderBy(String field) {
        if (this.orderBy == null) {
            this.orderBy = new ArrayList<>();
        }
        orderBy.add(field);
        return this;
    }



    public List<Order> getOrderBy(CriteriaBuilder criteriaBuilder, Root root) {
        if (orderBy.isEmpty()) return Collections.emptyList();

        List<Order> orderList = new ArrayList<>();
        for (String order : orderBy) {
            if (order.startsWith("-")) {
                orderList.add(criteriaBuilder.desc(get(root, order.substring(1))));
            } else {
                orderList.add(criteriaBuilder.asc(get(root, order)));
            }
        }

        return orderList;
    }

    private Path get(Root root, String fieldName) {
        String[] fields = fieldName.split("\\.");
        if (fields.length == 1) {
            return root.get(fieldName);
        } else {
            Path path = root.get(fields[0]);
            for (int count = 1; count < fields.length; count++) {
                path = path.get(fields[count]);
            }
            return path;
        }
    }

    /**
     * Build an equals predicate. Resulting SQL is similar to
     * - SELECT * FROM [table] WHERE [field] = [value]
     *
     * @param criteriaBuilder The CriteriaBuilder used for building the predicate
     * @param root            The JPA Root needed for building the predicate
     * @param field           The field name
     * @param value           The field value
     * @param <T>             The class of the value
     * @return The predicate for 'equal' operation
     */
    protected <T> Predicate equals(CriteriaBuilder criteriaBuilder, Root root, String field, T value) {
        if (value == null) return null;
        Predicate predicate = criteriaBuilder.equal(get(root, field), value);
        return predicate;
    }


    /**
     * Build an NOT equals predicate. Resulting SQL is similar to
     * - SELECT * FROM [table] WHERE [field] != [value]
     *
     * @param criteriaBuilder The CriteriaBuilder used for building the predicate
     * @param root            The JPA Root needed for building the predicate
     * @param field           The field name
     * @param value           The field value
     * @param <T>             The class of the value
     * @return The predicate for 'notEqual' operation
     */
    protected <T> Predicate notEquals(CriteriaBuilder criteriaBuilder, Root root, String field, T value) {
        if (value == null) return null;
        Predicate predicate = criteriaBuilder.notEqual(get(root, field), value);
        return predicate;
    }

    /**
     * Build an equals predicate that ignores case
     * - SELECT * FROM [table] WHERE UPPER([field]) = UPPER([value])
     *
     * @param criteriaBuilder The CriteriaBuilder used for building the predicate
     * @param root            The JPA Root needed for building the predicate
     * @param field           The field name
     * @param value           The field value
     * @return The predicate for 'equal' operation
     */
    protected Predicate equalsIgnoreCase(CriteriaBuilder criteriaBuilder, Root root, String field, String value) {
        if (value == null) return null;
        Predicate predicate = criteriaBuilder.equal(criteriaBuilder.upper(get(root, field)), value.toUpperCase());
        return predicate;
    }


    /**
     * Build a 'like' predicate. The field value will be "[any]value[any]"
     * - SELECT * FROM [table] WHERE [field] like '%[value]%'
     *
     * @param criteriaBuilder The CriteriaBuilder used for building the predicate
     * @param root            The JPA Root needed for building the predicate
     * @param field           The field name
     * @param value           The field value
     * @return The predicate for 'like' operation
     */
    protected Predicate like(CriteriaBuilder criteriaBuilder, Root root, String field, String value) {
        if (value == null) return null;
        Predicate predicate = criteriaBuilder.like(get(root, field), "%" + value + "%");
        return predicate;
    }

    /**
     * Build a 'like' predicate that ignores case. The field value will be "[any]value[any]".
     * - SELECT * FROM [table] WHERE UPPER([field]) like UPPER('%[value]%')
     *
     * @param criteriaBuilder The CriteriaBuilder used for building the predicate
     * @param root            The JPA Root needed for building the predicate
     * @param field           The field name
     * @param value           The field value
     * @return The predicate for 'like' operation
     */
    protected Predicate likeIgnoreCase(CriteriaBuilder criteriaBuilder, Root root, String field, String value) {
        if (value == null) return null;
        Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(get(root, field)), "%" + value.toUpperCase() + "%");
        return predicate;
    }

    protected Predicate in(CriteriaBuilder criteriaBuilder, Root root, String field, Object... values) {
        if (values == null || values.length == 0) return null;

        Predicate predicate = get(root, field).in(values);
        return predicate;
    }

}
