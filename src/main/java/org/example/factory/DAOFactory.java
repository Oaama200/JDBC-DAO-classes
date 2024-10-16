package org.example.factory;

import org.example.author.AuthorDAO;
import org.example.book.BookDAO;

public interface DAOFactory {
    BookDAO createBookDAO();
    AuthorDAO createAuthorDAO();
}