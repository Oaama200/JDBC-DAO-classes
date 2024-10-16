package org.example.services;

import org.example.book.Book;
import org.example.exception.ServiceException;

import java.util.List;

public interface BookService {
    Book getBookById(Long id) throws ServiceException;
    List<Book> getAllBooks() throws ServiceException;
    void addBook(Book book) throws ServiceException;
    void updateBook(Book book) throws ServiceException;
    void deleteBook(Long id) throws ServiceException;

}
