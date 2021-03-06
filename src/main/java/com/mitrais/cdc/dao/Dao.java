package com.mitrais.cdc.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    /**
     * Find single record by id.
     * @param id id
     * @return record wrapped by {@link Optional}
     */
    Optional<T> find(ID id);

    /**
     * Find all records
     * @return list of records
     */
    List<T> findAll();

    /**
     * Persist record to database
     * @param o record
     * @return true if operation success
     */
    boolean save(T o);

    /**
     * Persist existing record to database
     * @param o record
     * @return true if operation success
     */
    boolean update(T o);

    /**
     * Remove existing record from database
     * @param o record
     * @return true if operaion success
     */
    boolean delete(T o);
}
