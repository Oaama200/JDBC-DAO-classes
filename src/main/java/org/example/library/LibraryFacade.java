package org.example.library;

import org.example.author.Author;
import org.example.book.Book;
import org.example.exception.ServiceException;
import org.example.services.AuthorService;
import org.example.services.BookService;

import java.util.List;

public class LibraryFacade {
    private final AuthorService authorService;
    private final BookService bookService;

    public LibraryFacade(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public void addAuthor(Author author) throws ServiceException {
        authorService.addAuthor(author);
    }

    public void updateAuthor(Author author) throws ServiceException {
        authorService.updateAuthor(author);
    }

    public void deleteAuthor(Long id) throws ServiceException {
        authorService.deleteAuthor(id);
    }

    public Author getAuthorById(Long id) throws ServiceException {
        return authorService.getAuthorById(id);
    }

    public List<Author> getAllAuthors() throws ServiceException {
        return authorService.getAllAuthors();
    }

    public void addBook(Book book) throws ServiceException {
        bookService.addBook(book);
    }

    public void updateBook(Book book) throws ServiceException {
        bookService.updateBook(book);
    }

    public void deleteBook(Long id) throws ServiceException {
        bookService.deleteBook(id);
    }

    public Book getBookById(Long id) throws ServiceException {
        return bookService.getBookById(id);
    }

    public List<Book> getAllBooks() throws ServiceException {
        return bookService.getAllBooks();
    }
}