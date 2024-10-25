package org.example.services;

import org.example.book.Book;
import org.example.book.BookDAO;
import org.example.exception.DAOException;
import org.example.exception.ServiceException;

import java.util.List;

public class MyBatisBookService implements BookService {
    private final BookDAO bookDAO;

    public MyBatisBookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public Book getBookById(Long id) throws ServiceException {
        try {
            return bookDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Error getting book by ID", e);
        }
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        try {
            return bookDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Error getting all books", e);
        }
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        try {
            bookDAO.save(book);
        } catch (DAOException e) {
            throw new ServiceException("Error adding book", e);
        }
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        try {
            bookDAO.update(book);
        } catch (DAOException e) {
            throw new ServiceException("Error updating book", e);
        }
    }

    @Override
    public void deleteBook(Long id) throws ServiceException {
        try {
            bookDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting book", e);
        }
    }
}