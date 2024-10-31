package org.example.services;

import org.example.book.Book;
import org.example.exception.ServiceException;

import java.util.List;

public class BookServiceDecorator extends ServiceDecorator<Book> implements BookService {
    private final BookService bookService;

    public BookServiceDecorator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected void performOperation(String operationName, Runnable operation) throws ServiceException {
        try {
            operation.run();
        } catch (Exception e) {
            throw new ServiceException("Error " + operationName + " book", e);
        }
    }

    @Override
    protected boolean isValid(Book book) {
        return book != null && book.getIsbn() != null && !book.getIsbn().isEmpty()
                && book.getTitle() != null && !book.getTitle().isEmpty();
    }

    @Override
    public Book getBookById(Long id) throws ServiceException {
        logger.info("Getting book by ID: " + id);
        try {
            Book book = bookService.getBookById(id);
            logger.info("Retrieved book: " + book);
            return book;
        } catch (ServiceException e) {
            logger.severe("Error getting book by ID: " + id);
            throw e;
        }
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        logger.info("Getting all books");
        try {
            List<Book> books = bookService.getAllBooks();
            logger.info("Retrieved " + books.size() + " books");
            return books;
        } catch (ServiceException e) {
            logger.severe("Error getting all books");
            throw e;
        }
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        logAndValidate("adding", book, () -> {
            try {
                bookService.addBook(book);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        logAndValidate("updating", book, () -> {
            try {
                bookService.updateBook(book);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void deleteBook(Long id) throws ServiceException {
        logger.info("Deleting book with ID: " + id);
        try {
            bookService.deleteBook(id);
            logger.info("Book deleted successfully");
        } catch (ServiceException e) {
            logger.severe("Error deleting book with ID: " + id);
            throw e;
        }
    }
}