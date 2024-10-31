package org.example.library;

import org.example.author.Author;
import org.example.book.Book;
import org.example.exception.ServiceException;

import java.util.List;

public interface LibraryController {
    void addBook(Book book) throws ServiceException;
    void updateBook(Book book) throws ServiceException;
    void deleteBook(Long id) throws ServiceException;
    Book getBookById(Long id) throws ServiceException;
    List<Book> getAllBooks() throws ServiceException;

    void addAuthor(Author author) throws ServiceException;
    void updateAuthor(Author author) throws ServiceException;
    void deleteAuthor(Long id) throws ServiceException;
    Author getAuthorById(Long id) throws ServiceException;
    List<Author> getAllAuthors() throws ServiceException;
}
