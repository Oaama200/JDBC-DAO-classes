package org.example.book;

import org.example.exception.DAOException;
import org.example.factory.GenericDAO;

import java.util.List;

public interface BookDAO extends GenericDAO<Book, Long> {
    void save(Book book) throws DAOException;
    void update(Book book) throws DAOException;
    void delete(Long id) throws DAOException;
    Book findById(Long id) throws DAOException;
    List<Book> findAll() throws DAOException;
}



