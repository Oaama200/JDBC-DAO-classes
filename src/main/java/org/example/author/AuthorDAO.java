package org.example.author;

import org.example.exception.DAOException;
import org.example.factory.GenericDAO;

import java.util.List;

public interface AuthorDAO extends GenericDAO<Author, Long> {
    void save(Author author) throws DAOException;
    void update(Author author) throws DAOException;
    void delete(Long id) throws DAOException;
    Author findById(Long id) throws DAOException;
    List<Author> findAll() throws DAOException;
}