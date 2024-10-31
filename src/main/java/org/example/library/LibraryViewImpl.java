package org.example.library;

import org.example.author.Author;
import org.example.book.Book;

import java.util.List;

public class LibraryViewImpl implements LibraryView {
    @Override
    public void displayBooks(List<Book> books) {
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
        }
    }

    @Override
    public void displayAuthors(List<Author> authors) {
        System.out.println("Authors:");
        for (Author author : authors) {
            System.out.println("- " + author.getFirstName() + " " + author.getLastName());
        }
    }

    @Override
    public void displayBook(Book book) {
        System.out.println("Book Details:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Publication Year: " + book.getPublicationYear());
        System.out.println("Edition Number: " + book.getEditionNumber());
    }

    @Override
    public void displayAuthor(Author author) {
        System.out.println("Author Details:");
        System.out.println("Name: " + author.getFirstName() + " " + author.getLastName());
        System.out.println("Bio: " + author.getBio());
        System.out.println("Birth Date: " + author.getBirthDate());
    }

    @Override
    public void displayError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    @Override
    public void displaySuccess(String successMessage) {
        System.out.println("Success: " + successMessage);
    }
}