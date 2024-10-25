package org.example.services;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.author.AuthorDAO;
import org.example.author.MyBatisAuthorDAO;
import org.example.book.BookDAO;
import org.example.book.MyBatisBookDAO;
import org.example.factory.DAOFactory;

public class MyBatisDAOFactory implements DAOFactory {
    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisDAOFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public BookDAO createBookDAO() {
        return new MyBatisBookDAO(sqlSessionFactory);
    }

    @Override
    public AuthorDAO createAuthorDAO() {
        return new MyBatisAuthorDAO(sqlSessionFactory);
    }
}