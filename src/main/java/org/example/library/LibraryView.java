package org.example.library;

import org.example.author.Author;
import org.example.book.Book;

import java.util.List;

public interface LibraryView {
    void displayBooks(List<Book> books);
    void displayAuthors(List<Author> authors);
    void displayBook(Book book);
    void displayAuthor(Author author);
    void displayError(String errorMessage);
    void displaySuccess(String successMessage);
}