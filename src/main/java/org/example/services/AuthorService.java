package org.example.services;

import org.example.author.Author;
import org.example.exception.ServiceException;

import java.util.List;

public interface AuthorService {


    // Author-related methods
    Author getAuthorById(Long id) throws ServiceException;
    List<Author> getAllAuthors() throws ServiceException;
    void addAuthor(Author author) throws ServiceException;
    void updateAuthor(Author author) throws ServiceException;
    void deleteAuthor(Long id) throws ServiceException;
}
