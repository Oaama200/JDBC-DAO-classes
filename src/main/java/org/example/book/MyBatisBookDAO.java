package org.example.book;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.factory.AbstractMyBatisDAO;

public class MyBatisBookDAO extends AbstractMyBatisDAO<Book, Long> implements BookDAO {
    public MyBatisBookDAO(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    protected String getNamespace() {
        return "mappers.BookMapper";
    }
}