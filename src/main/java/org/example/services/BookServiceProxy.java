package org.example.services;

import org.example.book.Book;
import org.example.exception.ServiceException;

import java.util.List;


public class BookServiceProxy implements BookService {
    private final BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Book getBookById(Long id) throws ServiceException {
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid book ID", null);
        }
        return bookService.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        return bookService.getAllBooks();
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        if (isValidBook(book)) {
            bookService.addBook(book);
        } else {
            throw new ServiceException("Invalid book data", null);
        }
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        if (isValidBook(book) && book.getBookId() != null) {
            bookService.updateBook(book);
        } else {
            throw new ServiceException("Invalid book data for update", null);
        }
    }

    @Override
    public void deleteBook(Long id) throws ServiceException {
        if (id == null || id <= 0) {
            throw new ServiceException("Invalid book ID for deletion", null);
        }
        bookService.deleteBook(id);
    }

    private boolean isValidBook(Book book) {
        return book != null && book.getIsbn() != null && !book.getIsbn().isEmpty()
                && book.getTitle() != null && !book.getTitle().isEmpty();
    }
}