package org.example.library;

import org.example.author.Author;
import org.example.book.Book;
import org.example.exception.ServiceException;

import java.util.List;

public class LibraryControllerImpl implements LibraryController {
    private final LibraryFacade libraryFacade;
    private final LibraryView libraryView;

    public LibraryControllerImpl(LibraryFacade libraryFacade, LibraryView libraryView) {
        this.libraryFacade = libraryFacade;
        this.libraryView = libraryView;
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        try {
            libraryFacade.addBook(book);
            libraryView.displaySuccess("Book added successfully: " + book.getTitle());
        } catch (ServiceException e) {
            libraryView.displayError("Error adding book: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        try {
            libraryFacade.updateBook(book);
            libraryView.displaySuccess("Book updated successfully: " + book.getTitle());
        } catch (ServiceException e) {
            libraryView.displayError("Error updating book: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteBook(Long id) throws ServiceException {
        try {
            libraryFacade.deleteBook(id);
            libraryView.displaySuccess("Book deleted successfully with ID: " + id);
        } catch (ServiceException e) {
            libraryView.displayError("Error deleting book: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Book getBookById(Long id) throws ServiceException {
        try {
            Book book = libraryFacade.getBookById(id);
            libraryView.displayBook(book);
            return book;
        } catch (ServiceException e) {
            libraryView.displayError("Error retrieving book: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        try {
            List<Book> books = libraryFacade.getAllBooks();
            libraryView.displayBooks(books);
            return books;
        } catch (ServiceException e) {
            libraryView.displayError("Error retrieving all books: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void addAuthor(Author author) throws ServiceException {
        try {
            libraryFacade.addAuthor(author);
            libraryView.displaySuccess("Author added successfully: " + author.getFirstName() + " " + author.getLastName());
        } catch (ServiceException e) {
            libraryView.displayError("Error adding author: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateAuthor(Author author) throws ServiceException {
        try {
            libraryFacade.updateAuthor(author);
            libraryView.displaySuccess("Author updated successfully: " + author.getFirstName() + " " + author.getLastName());
        } catch (ServiceException e) {
            libraryView.displayError("Error updating author: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteAuthor(Long id) throws ServiceException {
        try {
            libraryFacade.deleteAuthor(id);
            libraryView.displaySuccess("Author deleted successfully with ID: " + id);
        } catch (ServiceException e) {
            libraryView.displayError("Error deleting author: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Author getAuthorById(Long id) throws ServiceException {
        try {
            Author author = libraryFacade.getAuthorById(id);
            libraryView.displayAuthor(author);
            return author;
        } catch (ServiceException e) {
            libraryView.displayError("Error retrieving author: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        try {
            List<Author> authors = libraryFacade.getAllAuthors();
            libraryView.displayAuthors(authors);
            return authors;
        } catch (ServiceException e) {
            libraryView.displayError("Error retrieving all authors: " + e.getMessage());
            throw e;
        }
    }
}