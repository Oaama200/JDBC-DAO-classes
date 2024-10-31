package org.example.book;

import java.time.Year;

public class BookBuilder {
    private Book book = new Book();

    public BookBuilder setBookId(Long bookId) {
        book.setBookId(bookId);
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        book.setIsbn(isbn);
        return this;
    }

    public BookBuilder setTitle(String title) {
        book.setTitle(title);
        return this;
    }

    public BookBuilder setPublicationYear(Year publicationYear) {
        book.setPublicationYear(publicationYear);
        return this;
    }

    public BookBuilder setEditionNumber(int editionNumber) {
        book.setEditionNumber(editionNumber);
        return this;
    }

    public Book build() {
        return book;
    }
}