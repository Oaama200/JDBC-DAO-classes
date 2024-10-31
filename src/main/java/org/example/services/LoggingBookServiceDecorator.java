package org.example.services;

import org.example.book.Book;
import org.example.exception.ServiceException;

import java.util.List;
import java.util.logging.Logger;

public class LoggingBookServiceDecorator implements BookService {
    private final BookService bookService;
    private final Logger logger = Logger.getLogger(LoggingBookServiceDecorator.class.getName());

    public LoggingBookServiceDecorator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Book getBookById(Long id) throws ServiceException {
        logger.info("Getting book by ID: " + id);
        Book book = bookService.getBookById(id);
        logger.info("Retrieved book: " + book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        logger.info("Getting all books");
        List<Book> books = bookService.getAllBooks();
        logger.info("Retrieved " + books.size() + " books");
        return books;
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        logger.info("Adding book: " + book);
        bookService.addBook(book);
        logger.info("Book added successfully");
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        logger.info("Updating book: " + book);
        bookService.updateBook(book);
        logger.info("Book updated successfully");
    }

    @Override
    public void deleteBook(Long id) throws ServiceException {
        logger.info("Deleting book with ID: " + id);
        bookService.deleteBook(id);
        logger.info("Book deleted successfully");
    }
}