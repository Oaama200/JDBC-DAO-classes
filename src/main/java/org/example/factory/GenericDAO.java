package org.example.factory;

import org.example.exception.DAOException;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id) throws DAOException;
    List<T> findAll() throws DAOException;
    void save(T entity) throws DAOException;
    void update(T entity) throws DAOException;
    void delete(ID id) throws DAOException;
}