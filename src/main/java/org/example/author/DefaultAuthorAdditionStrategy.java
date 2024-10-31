package org.example.author;

import org.example.exception.DAOException;
import org.example.exception.ServiceException;

public class DefaultAuthorAdditionStrategy implements AuthorAdditionStrategy {
    private final AuthorDAO authorDAO;

    public DefaultAuthorAdditionStrategy(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public void addAuthor(Author author) throws ServiceException {
        try {
            authorDAO.save(author);
        } catch (DAOException e) {
            throw new ServiceException("Error adding author", e);
        }
    }
}