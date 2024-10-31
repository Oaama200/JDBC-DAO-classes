package org.example.author;

import org.example.exception.ServiceException;

public interface AuthorAdditionStrategy {
    void addAuthor(Author author) throws ServiceException;
}
