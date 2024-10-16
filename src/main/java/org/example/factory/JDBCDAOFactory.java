package org.example.factory;

import org.example.author.AuthorDAO;
import org.example.author.JDBCAuthorDAO;
import org.example.book.BookDAO;
import org.example.book.JDBCBookDAO;
import org.example.connection.ConnectionPool;
import org.example.factory.DAOFactory;

public class JDBCDAOFactory implements DAOFactory {
    private final ConnectionPool connectionPool;

    public JDBCDAOFactory(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public BookDAO createBookDAO() {
        return new JDBCBookDAO(connectionPool);
    }
    @Override
    public AuthorDAO createAuthorDAO() { // Implement this method
        return new JDBCAuthorDAO(connectionPool);
    }
}