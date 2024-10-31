package org.example.services;
import org.example.author.Author;
import org.example.author.AuthorEventNotifier;
import org.example.exception.ServiceException;

import java.util.List;

public class AuthorServiceDecorator extends ServiceDecorator<Author> implements AuthorService {
    private final AuthorService authorService;
    private final AuthorEventNotifier eventNotifier;

    public AuthorServiceDecorator(AuthorService authorService, AuthorEventNotifier eventNotifier) {
        this.authorService = authorService;
        this.eventNotifier = eventNotifier;
    }

    @Override
    public void performOperation(String operationName, Runnable operation) throws ServiceException {
        try {
            operation.run();
        } catch (Exception e) {
            throw new ServiceException("Error " + operationName + " author", e);
        }
    }

    @Override
    public boolean isValid(Author author) {
        return author != null && author.getFirstName() != null && !author.getFirstName().isEmpty()
                && author.getLastName() != null && !author.getLastName().isEmpty();
    }

    @Override
    public Author getAuthorById(Long id) throws ServiceException {
        logger.info("Getting author by ID: " + id);
        try {
            Author author = authorService.getAuthorById(id);
            logger.info("Retrieved author: " + author);
            return author;
        } catch (ServiceException e) {
            logger.severe("Error getting author by ID: " + id);
            throw e;
        }
    }

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        logger.info("Getting all authors");
        try {
            List<Author> authors = authorService.getAllAuthors();
            logger.info("Retrieved " + authors.size() + " authors");
            return authors;
        } catch (ServiceException e) {
            logger.severe("Error getting all authors");
            throw e;
        }
    }

    @Override
    public void addAuthor(Author author) throws ServiceException {
        logAndValidate("adding", author, () -> {
            try {
                authorService.addAuthor(author);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            eventNotifier.notifyAuthorAdded(author);
        });
    }

    @Override
    public void updateAuthor(Author author) throws ServiceException {
        logAndValidate("updating", author, () -> {
            try {
                authorService.updateAuthor(author);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
            eventNotifier.notifyAuthorUpdated(author);
        });
    }

    @Override
    public void deleteAuthor(Long id) throws ServiceException {
        logger.info("Deleting author with ID: " + id);
        try {
            authorService.deleteAuthor(id);
            logger.info("Author deleted successfully");
            eventNotifier.notifyAuthorDeleted(id);
        } catch (ServiceException e) {
            logger.severe("Error deleting author with ID: " + id);
            throw e;
        }
    }
}