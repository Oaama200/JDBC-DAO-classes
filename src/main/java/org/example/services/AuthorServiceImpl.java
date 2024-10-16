package org.example.services;


import org.example.author.Author;
import org.example.author.AuthorDAO;
import org.example.exception.DAOException;
import org.example.exception.ServiceException;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO authorDAO;
    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }


    // Author-related methods
    @Override
    public void addAuthor(Author author) throws ServiceException {
        try {
            authorDAO.save(author);
        } catch (DAOException e) {
            throw new ServiceException("Error adding author", e);
        }
    }

    @Override
    public void updateAuthor(Author author) throws ServiceException {
        try {
            authorDAO.update(author);
        } catch (DAOException e) {
            throw new ServiceException("Error updating author", e);
        }
    }

    @Override
    public void deleteAuthor(Long id) throws ServiceException {
        try {
            authorDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting author with ID: " + id, e);
        }
    }

    @Override
    public Author getAuthorById(Long id) throws ServiceException {
        try {
            return authorDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Error getting author by ID", e);
        }
    }

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        try {
            return authorDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Error getting all authors", e);
        }
    }
}
